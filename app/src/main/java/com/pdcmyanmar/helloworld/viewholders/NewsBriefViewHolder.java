package com.pdcmyanmar.helloworld.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdcmyanmar.helloworld.R;
import com.pdcmyanmar.helloworld.data.vos.NewsVO;
import com.pdcmyanmar.helloworld.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsBriefViewHolder extends BaseNewsViewHolder {

    @BindView(R.id.tv_publication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.tv_news_brief)
    TextView tvNewsBrief;

    @BindView(R.id.iv_news_hero)
    ImageView ivNewsHero;

    //private NewsVO mNews;

    public NewsBriefViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(NewsVO news) {
        //mNews = news;
        mData = news;
        tvNewsBrief.setText(news.getBrief());
        tvPublicationTitle.setText(news.getPublication().getTitle());
        if (!news.getImages().isEmpty()) {
            GlideApp.with(ivNewsHero.getContext())
                    .load(news.getImages().get(0))
                    .placeholder(R.drawable.placeholder_images)
                    .error(R.drawable.error)
                    .into(ivNewsHero);
        }

    }
}
