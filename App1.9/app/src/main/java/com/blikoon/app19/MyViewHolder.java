package com.blikoon.app19;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    final Button refreshButton;
    final TextView textView;

    MyViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.item_text);
        refreshButton = (Button) itemView.findViewById(R.id.refresh_button);
    }
}
