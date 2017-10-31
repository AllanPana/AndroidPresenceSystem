package com.example.allan.androidpresencesystem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Allan on 31/10/2017.
 */

class ListOnlineViewHolder extends RecyclerView.ViewHolder {

    private TextView tvEmail;
    public ListOnlineViewHolder(View itemView) {
        super(itemView);

        tvEmail = itemView.findViewById(R.id.tvEmail);
    }
}