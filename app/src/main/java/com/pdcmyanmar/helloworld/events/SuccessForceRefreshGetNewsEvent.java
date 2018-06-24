package com.pdcmyanmar.helloworld.events;

import com.pdcmyanmar.helloworld.data.vos.NewsVO;

import java.util.List;

public class SuccessForceRefreshGetNewsEvent extends SuccessGetNewsEvent {

    public SuccessForceRefreshGetNewsEvent(List<NewsVO> newsList) {
        super(newsList);
    }
}
