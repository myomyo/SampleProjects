package com.pdcmyanmar.helloworld.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdcmyanmar.helloworld.R;
import com.pdcmyanmar.helloworld.data.vos.NewsVO;
import com.pdcmyanmar.helloworld.delegates.NewsDelegate;
import com.pdcmyanmar.helloworld.viewholders.BaseNewsViewHolder;
import com.pdcmyanmar.helloworld.viewholders.NewsBriefViewHolder;
import com.pdcmyanmar.helloworld.viewholders.NewsViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class NewsAdapter extends RecyclerView.Adapter<BaseNewsViewHolder> {
    private NewsDelegate mNewsDelegate;
    private List<NewsVO> mNewsList;

    private static final int VT_NEWS_COMPLETE =1000;
    private static final int VT_NEWS_BRIEF =2000;


    public NewsAdapter(NewsDelegate newsDelegate){
        mNewsDelegate = newsDelegate;
        mNewsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if(viewType == VT_NEWS_COMPLETE){
            View view = layoutInflater.inflate(R.layout.view_holder_news, parent, false);
            return new NewsViewHolder(view, mNewsDelegate);
        }else if(viewType == VT_NEWS_BRIEF){
            View view = layoutInflater.inflate(R.layout.view_holder_news_brief, parent, false);
            return new NewsBriefViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseNewsViewHolder holder, int position) {
        holder.bindData(mNewsList.get(position));
    }


    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setNewsList(List<NewsVO> newsList){
        mNewsList = newsList;
        notifyDataSetChanged();
    }

    public void appendNewsList(List<NewsVO> newsList){

        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VT_NEWS_COMPLETE;
        }else {
            return VT_NEWS_BRIEF;
        }

    }
}
