package com.pdcmyanmar.helloworld.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.pdcmyanmar.helloworld.data.vos.NewsVO;
import com.pdcmyanmar.helloworld.viewholders.BaseNewsViewHolder;
import com.pdcmyanmar.helloworld.viewholders.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<VH extends BaseViewHolder<D>, D> extends RecyclerView.Adapter<VH> { // VH : view holder, D: data

    protected List<D> mData;

    public BaseRecyclerAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bindData(mData.get(position));
    }

    public void setNewsList(List<D> data){
        mData = data;
        notifyDataSetChanged();
    }

    public void appendNewsList(List<D> data){

        mData.addAll(data);
        notifyDataSetChanged();
    }
}
