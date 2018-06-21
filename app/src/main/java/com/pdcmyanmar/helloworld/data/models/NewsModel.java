package com.pdcmyanmar.helloworld.data.models;

import com.pdcmyanmar.helloworld.data.vos.NewsVO;
import com.pdcmyanmar.helloworld.events.SuccessGetNewsEvent;
import com.pdcmyanmar.helloworld.network.HttpUrlConnectionDataAgentImpl;
import com.pdcmyanmar.helloworld.network.NewsDataAgent;
import com.pdcmyanmar.helloworld.network.OkHttpDataAgentImpl;
import com.pdcmyanmar.helloworld.network.RetrofitDataAgentImpl;
import com.pdcmyanmar.helloworld.utils.MMNewsConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class NewsModel {

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";
    private static NewsModel objInstance;

    private NewsDataAgent mDataAgent;

    private Map<String, NewsVO> mNewsMap;

    private NewsModel() {
        //mDataAgent = HttpUrlConnectionDataAgentImpl.getObjInstance();
       // mDataAgent = OkHttpDataAgentImpl.getObjInstance();
        mDataAgent = RetrofitDataAgentImpl.getObjInstance();
        mNewsMap = new HashMap<>();
        EventBus.getDefault().register(this);
    }

    public static NewsModel getObjInstance(){
        if(objInstance == null){
            objInstance = new NewsModel();
        }
        return objInstance;
    }

    public void loadNewsList(){

            mDataAgent.loadNewsList(1, DUMMY_ACCESS_TOKEN);
    }

    public NewsVO getNewsById(String newsId){

        return mNewsMap.get(newsId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNews(SuccessGetNewsEvent event){
        for(NewsVO news : event.getNewsList()){
            mNewsMap.put(news.getNewsId(), news);
        }
    }
}
