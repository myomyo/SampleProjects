package com.pdcmyanmar.helloworld.network;

import com.pdcmyanmar.helloworld.events.ApiErrorEvent;
import com.pdcmyanmar.helloworld.events.SuccessGetNewsEvent;
import com.pdcmyanmar.helloworld.network.response.GetNewsResponse;
import com.pdcmyanmar.helloworld.utils.MMNewsConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements NewsDataAgent {

    private static RetrofitDataAgentImpl sObjInstance;

    private NewsApi mNewsApi;

    public RetrofitDataAgentImpl() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MMNewsConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mNewsApi = retrofit.create(NewsApi.class);
    }

    public static RetrofitDataAgentImpl getObjInstance() {

        if (sObjInstance == null) {
            sObjInstance = new RetrofitDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadNewsList(int page, String accessToken) {
        Call<GetNewsResponse> loadNewsCall = mNewsApi.loadNewsList(accessToken, page);
        loadNewsCall.enqueue(new Callback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                GetNewsResponse newsResponse = response.body();
                if (newsResponse != null && newsResponse.isResponseOK()) {
                    SuccessGetNewsEvent event = new SuccessGetNewsEvent(newsResponse.getMmNews());
                    EventBus.getDefault().post(event);
                } else {
                    if (newsResponse == null) {
                        ApiErrorEvent event = new ApiErrorEvent("Empty response in network call.");
                        EventBus.getDefault().post(event);
                    } else {
                        ApiErrorEvent event = new ApiErrorEvent(newsResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }

            }

            @Override
            public void onFailure(Call<GetNewsResponse> call, Throwable t) {

                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }
}
