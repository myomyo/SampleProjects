package com.pdcmyanmar.helloworld.delegates;

import com.pdcmyanmar.helloworld.data.vos.NewsVO;

public interface NewsDelegate {

    void onTapNews(NewsVO news);
    void onTapFavorite(NewsVO news);
    void onTapComment(NewsVO news);
    void onTapSendTo(NewsVO news);
    void onTapStatistics(NewsVO news);

}
