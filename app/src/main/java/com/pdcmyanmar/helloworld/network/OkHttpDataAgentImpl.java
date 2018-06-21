package com.pdcmyanmar.helloworld.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.pdcmyanmar.helloworld.events.ApiErrorEvent;
import com.pdcmyanmar.helloworld.events.SuccessGetNewsEvent;
import com.pdcmyanmar.helloworld.network.response.GetNewsResponse;
import com.pdcmyanmar.helloworld.utils.MMNewsConstants;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDataAgentImpl implements NewsDataAgent {

    private static OkHttpDataAgentImpl sObjInstance;

    private OkHttpClient mHttpClient;

    public OkHttpDataAgentImpl() {
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpDataAgentImpl getObjInstance() {
        if(sObjInstance == null){
            sObjInstance = new OkHttpDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadNewsList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {

                RequestBody formBody = new FormBody.Builder()
                        .add(MMNewsConstants.PARAM_ACCESS_TOKEN, accessToken)
                        .add(MMNewsConstants.PAGE, String.valueOf(page))
                        .build();

                Request request = new Request.Builder()
                        .url(MMNewsConstants.API_BASE + MMNewsConstants.GET_NEWS)
                        .post(formBody)
                        .build();

                try {
                    Response response = mHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();
                        return responseString;
                    }
                } catch (IOException e) {
                    Log.d("loadTalksList", e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetNewsResponse newsResponse = gson.fromJson(responseString, GetNewsResponse.class);
                Log.d("onPostExecute", "Talks List Size : "+ newsResponse.getMmNews().size());
                if(newsResponse.isResponseOK()){
                    SuccessGetNewsEvent event = new SuccessGetNewsEvent(newsResponse.getMmNews());
                    EventBus.getDefault().post(event);
                } else {
                    ApiErrorEvent event = new ApiErrorEvent(newsResponse.getMessage());
                    EventBus.getDefault().post(event);
                }
            }
        }.execute();
    }
}
