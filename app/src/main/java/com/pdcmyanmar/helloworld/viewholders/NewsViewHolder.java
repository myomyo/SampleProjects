package com.pdcmyanmar.helloworld.viewholders;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pdcmyanmar.helloworld.R;
import com.pdcmyanmar.helloworld.data.vos.NewsVO;
import com.pdcmyanmar.helloworld.delegates.NewsDelegate;

import org.mmtextview.components.MMTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private NewsDelegate mNewsDelegate;
    private NewsVO mNews;

    @BindView(R.id.tv_news_brief)
    TextView tvNewsBrief;

    @BindView(R.id.tv_posted_date)
    TextView tvPostedDate;

    @BindView(R.id.tv_publication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.iv_publication)
    ImageView ivPublicationLogo;

    @Nullable
    @BindView(R.id.iv_news_hero)
    ImageView ivNewsLogo;


    public NewsViewHolder(View itemView, NewsDelegate newsDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mNewsDelegate = newsDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewsDelegate.onTapNews();
            }
        });
    }

    public void setNewsData(NewsVO news) {
        mNews = news;
        tvNewsBrief.setText(news.getBrief());
        tvPostedDate.setText("Posted at : "+news.getPostedDate());
        tvPublicationTitle.setText(news.getPublication().getTitle());
        Glide.with(ivPublicationLogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivPublicationLogo);
        if(news.getImages() != null){
            Glide.with(ivNewsLogo.getContext())
                    .load(news.getImages().get(0))
                    .into(ivNewsLogo);
        }


    }

}
