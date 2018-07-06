package com.pdcmyanmar.helloworld.viewholders;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pdcmyanmar.helloworld.R;
import com.pdcmyanmar.helloworld.data.vos.NewsVO;
import com.pdcmyanmar.helloworld.delegates.NewsDelegate;
import com.pdcmyanmar.helloworld.utils.GlideApp;

import org.mmtextview.components.MMTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsViewHolder extends BaseNewsViewHolder {

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
                mNewsDelegate.onTapNews(mNews);
            }
        });
    }

    @Override
    public void bindData(NewsVO news) {
        super.bindData(news);
        mNews = news;
        tvNewsBrief.setText(news.getBrief());
        tvPostedDate.setText(tvPostedDate.getContext().getResources().getString(R.string.format_posted_date, news.getPostedDate()));

        tvPublicationTitle.setText(news.getPublication().getTitle());
        GlideApp.with(ivPublicationLogo.getContext())
                .load(news.getPublication().getLogo())
                .apply(RequestOptions
                        .circleCropTransform()
                        .placeholder(R.drawable.placeholder_images)
                        .error(R.drawable.error))
                .into(ivPublicationLogo);

        if (!news.getImages().isEmpty()) {
            GlideApp.with(ivNewsLogo.getContext())
                    .load(news.getImages().get(0))
                    .placeholder(R.drawable.placeholder_images)
                    .error(R.drawable.error)
                    .into(ivNewsLogo);
        } else {
            ivNewsLogo.setVisibility(View.GONE);
        }
    }


}
