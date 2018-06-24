package com.pdcmyanmar.helloworld.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pdcmyanmar.helloworld.R;
import com.pdcmyanmar.helloworld.data.models.NewsModel;
import com.pdcmyanmar.helloworld.data.vos.NewsVO;
import com.pdcmyanmar.helloworld.viewpods.EmptyViewPod;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_news_details)
    TextView tvNewsDetails;

    @BindView(R.id.iv_publication_logo_details)
    ImageView ivPublicationLogo;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.iv_news_backdrop)
    ImageView ivNewsBackdrop;

    @BindView(R.id.tv_posted_date_details)
    TextView tvPostedDate;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this, this);

        String newsId = getIntent().getStringExtra("newsId");
        Log.d("NewsDetailsActivity", "NewsId : " + newsId);

        NewsVO news = NewsModel.getObjInstance().getNewsById(newsId);

        if(news != null){
            bindData(news);
        }else {
            vpEmpty.setVisibility(View.VISIBLE);
            coordinatorLayout.setVisibility(View.GONE);

            String imageUrl ="https://i.pinimg.com/736x/ae/8a/c2/ae8ac2fa217d23aadcc913989fcc34a2---page-empty-page.jpg";

            //vpEmpty.setEmptyData(R.drawable.empty_data_placeholder, getString(R.string.empty_msg));
            vpEmpty.setEmptyData(imageUrl, getString(R.string.empty_msg));
        }


    }

    private void bindData(NewsVO news) {
        tvNewsDetails.setText(news.getDetails());
        tvTitle.setText(news.getPublication().getTitle());
        tvPostedDate.setText(tvPostedDate.getContext().getResources().getString(R.string.format_posted_date, news.getPostedDate()));
        Glide.with(ivPublicationLogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivPublicationLogo);

        if (!news.getImages().isEmpty()) {
            Glide.with(ivNewsBackdrop.getContext())
                    .load(news.getImages().get(0))
                    .into(ivNewsBackdrop);
        } else {
            ivNewsBackdrop.setVisibility(View.INVISIBLE);
        }
    }
}
