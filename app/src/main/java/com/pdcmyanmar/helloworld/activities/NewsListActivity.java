package com.pdcmyanmar.helloworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.pdcmyanmar.helloworld.R;
import com.pdcmyanmar.helloworld.adapters.NewsAdapter;
import com.pdcmyanmar.helloworld.data.models.NewsModel;
import com.pdcmyanmar.helloworld.data.vos.NewsVO;
import com.pdcmyanmar.helloworld.delegates.NewsDelegate;
import com.pdcmyanmar.helloworld.events.ApiErrorEvent;
import com.pdcmyanmar.helloworld.events.SuccessGetNewsEvent;
import com.pdcmyanmar.helloworld.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.mmtextview.MMFontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListActivity extends BaseActivity implements NewsDelegate {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    private NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        ButterKnife.bind(this, this);

        //To view the myanmar font in samsung J series device
        MMFontUtils.initMMTextView(this);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //RecyclerView rvNews = findViewById(R.id.rv_news);
        mNewsAdapter = new NewsAdapter(this);

        // For pagination
        rvNews.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private boolean isListEndReached = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("NewsListActivity", "onScrollListener:onScrollStateChanged : " + newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1
                        && !isListEndReached) {

                    isListEndReached = true;
                    NewsModel.getObjInstance().loadNewsList();

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("NewsListActivity", "onScrollListener:onScrolled , dx : " + dx + ", dy : " + dy);

                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int pastVisibleItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if ((visibleItemCount + pastVisibleItems) < totalItemCount) {
                    isListEndReached = false;
                }
            }
        }); // for pagination

        rvNews.setAdapter(mNewsAdapter);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        NewsModel.getObjInstance().loadNewsList();
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewsModel.getObjInstance().forceRefreshNewsList();
            }
        });

        String imageUrl = "https://i.pinimg.com/736x/ae/8a/c2/ae8ac2fa217d23aadcc913989fcc34a2---page-empty-page.jpg";

        //vpEmpty.setEmptyData(R.drawable.empty_data_placeholder, getString(R.string.empty_msg));
        vpEmpty.setEmptyData(imageUrl, getString(R.string.empty_msg));

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapNews(NewsVO news) {

        Intent intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
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
    public void onSuccessGetNews(SuccessGetNewsEvent event) {
        Log.d("onSuccessGetNews", "onSuccessGetNews :" + event.getNewsList());
        mNewsAdapter.appendNewsList(event.getNewsList());
        swipeRefreshLayout.setRefreshing(false);
        vpEmpty.setVisibility(View.GONE);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailGetNews(ApiErrorEvent event) {

        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(swipeRefreshLayout, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
        vpEmpty.setVisibility(View.VISIBLE);
        rvNews.setVisibility(View.GONE);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessForceRefreshGetNews(SuccessGetNewsEvent event){
        mNewsAdapter.setNewsList(event.getNewsList());
        swipeRefreshLayout.setRefreshing(false);
    }
}

