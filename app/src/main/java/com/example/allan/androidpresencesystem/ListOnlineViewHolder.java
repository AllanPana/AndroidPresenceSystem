package com.example.allan.androidpresencesystem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Allan on 31/10/2017.
 */

class ListOnlineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView tvEmail;
    ItemClickListener itemClickListener;
    public ListOnlineViewHolder(View itemView) {
        super(itemView);

        //setItemClickListener(itemClickListener);
        tvEmail = itemView.findViewById(R.id.tvEmail);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }
}