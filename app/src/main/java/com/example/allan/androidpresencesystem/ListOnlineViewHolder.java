package com.example.allan.androidpresencesystem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Allan on 31/10/2017.
 */

public class ListOnlineViewHolder extends RecyclerView.ViewHolder {

    protected TextView tvEmail;
    ItemClickListener itemClickListener;
    public ListOnlineViewHolder(View itemView) {
        super(itemView);

        tvEmail = itemView.findViewById(R.id.tvEmail);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(v, getAdapterPosition());
            }
        });

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}