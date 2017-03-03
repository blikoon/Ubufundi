package com.blikoon.app111;


import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SectionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM = 1;
    public static final int HEADER = 2;
    private List<Pair<String, String[]>> sections = new ArrayList<>();
    private Context mContext;

    public SectionsAdapter(Context context)
    {
        mContext = context;
    }

    public void addSection(String header, String[] items) {
        sections.add(Pair.create(header, items));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        switch (viewType) {
            case ITEM:
                viewHolder = new ItemHolder(inflater.inflate(R.layout.list_item, parent, false));
                break;
            case HEADER:
                viewHolder = new HeaderHolder(inflater.inflate(R.layout.list_header, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int steps = 0;
        for (int i = 0; i < sections.size(); i++) {
            Pair<String, String[]> section = sections.get(i);
            if(steps == position) {
                ((HeaderHolder) holder).getView().setText(section.first);
                return;
            }
            steps++;

            for (int j = 0; j < section.second.length; j++) {
                if(steps == position) {
                    ((ItemHolder) holder).getView().setText(section.second[j]);
                    return;
                }
                steps++;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int steps = 0;
        for (int i = 0; i < sections.size(); i++) {
            Pair<String, String[]> section = sections.get(i);
            if(steps == position) {
                return HEADER;
            }
            steps++;

            for (int j = 0; j < section.second.length; j++) {
                if(steps == position) {
                    return ITEM;
                }
                steps++;
            }
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (Pair<String, String[]> section : sections) {
            count += section.second.length + 1;
        }
        return count;
    }
}