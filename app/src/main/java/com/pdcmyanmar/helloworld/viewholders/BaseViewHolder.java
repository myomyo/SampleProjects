package com.pdcmyanmar.helloworld.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected T mData;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(T data);
}
