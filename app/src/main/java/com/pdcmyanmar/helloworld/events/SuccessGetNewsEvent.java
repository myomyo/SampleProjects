package com.pdcmyanmar.helloworld.events;

import com.pdcmyanmar.helloworld.data.vos.NewsVO;

import java.util.List;

public class SuccessGetNewsEvent {

    private List<NewsVO> newsList;

    public SuccessGetNewsEvent(List<NewsVO> newsList) {
        this.newsList = newsList;
    }

    public List<NewsVO> getNewsList() {
        return newsList;
    }
}
