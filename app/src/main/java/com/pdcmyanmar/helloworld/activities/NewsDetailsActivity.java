package com.pdcmyanmar.helloworld.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.pdcmyanmar.helloworld.R;
import com.pdcmyanmar.helloworld.data.models.NewsModel;
import com.pdcmyanmar.helloworld.data.vos.NewsVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_news_details)
    TextView tvNewsDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this,this);

        String newsId = getIntent().getStringExtra("newsId");
        Log.d("NewsDetailsActivity", "NewsId : " + newsId);

        NewsVO news = NewsModel.getObjInstance().getNewsById(newsId);
        bindData(news);
    }

    private void bindData(NewsVO news){
        tvNewsDetails.setText(news.getDetails());
    }
}
