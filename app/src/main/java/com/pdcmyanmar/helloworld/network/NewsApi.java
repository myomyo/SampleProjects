package com.pdcmyanmar.helloworld.network;

import com.pdcmyanmar.helloworld.network.response.GetNewsResponse;
import com.pdcmyanmar.helloworld.utils.MMNewsConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NewsApi {

        @FormUrlEncoded
        @POST(MMNewsConstants.GET_NEWS)
        Call<GetNewsResponse> loadNewsList(
                @Field(MMNewsConstants.PARAM_ACCESS_TOKEN) String accessToken,
                @Field(MMNewsConstants.PAGE) int page);
}
