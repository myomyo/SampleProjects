package com.pdcmyanmar.helloworld.data.models;

import com.pdcmyanmar.helloworld.network.HttpUrlConnectionDataAgentImpl;
import com.pdcmyanmar.helloworld.network.NewsDataAgent;
import com.pdcmyanmar.helloworld.utils.MMNewsConstants;

public class NewsModel {

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";
    private static NewsModel objInstance;

    private NewsDataAgent mDataAgent;

    private NewsModel() {
        mDataAgent = HttpUrlConnectionDataAgentImpl.getObjInstance();
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
}
