package com.pdcmyanmar.helloworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.pdcmyanmar.helloworld.R;
import com.pdcmyanmar.helloworld.adapters.NewsAdapter;
import com.pdcmyanmar.helloworld.data.models.NewsModel;
import com.pdcmyanmar.helloworld.data.vos.NewsVO;
import com.pdcmyanmar.helloworld.delegates.NewsDelegate;
import com.pdcmyanmar.helloworld.events.SuccessGetNewsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.mmtextview.MMFontUtils;

public class NewsListActivity extends BaseActivity implements NewsDelegate {

    private NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        //To view the myanmar font in samsung J series device
            MMFontUtils.initMMTextView(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvNews = findViewById(R.id.rv_news);
        mNewsAdapter = new NewsAdapter(this);
        rvNews.setAdapter(mNewsAdapter);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        NewsModel.getObjInstance().loadNewsList();

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapNews(NewsVO news) {

        Intent intent = new Intent(getApplicationContext(),NewsDetailsActivity.class);
        intent.putExtra("newsId", news.getNewsId());
        startActivity(intent);
    }

    @Override
    public void onTapFavorite(NewsVO news) {

    }

    @Override
    public void onTapComment(NewsVO news) {

    }

    @Override
    public void onTapSendTo(NewsVO news) {

    }

    @Override
    public void onTapStatistics(NewsVO news) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNews(SuccessGetNewsEvent event){
        Log.d("onSuccessGetNews", "onSuccessGetNews :" + event.getNewsList());
        mNewsAdapter.setNewsList(event.getNewsList());

    }

}

