package com.pdcmyanmar.helloworld.network.response;

import com.google.gson.annotations.SerializedName;
import com.pdcmyanmar.helloworld.data.vos.NewsVO;

import java.util.List;

public class GetNewsResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("mmNews")
    private List<NewsVO> mmNews;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<NewsVO> getMmNews() {
        return mmNews;
    }

    public boolean isResponseOK(){
        return (code == 200 && mmNews != null);
    }
}
