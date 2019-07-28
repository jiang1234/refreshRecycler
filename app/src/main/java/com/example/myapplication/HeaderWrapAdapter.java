package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HeaderWrapAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RecyclerView.Adapter adapter;
    View view;
    public HeaderWrapAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0) {
            return new MyHeaderVH(view);
        } else {
            return adapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(isHeader(position)) {

        }else {
            adapter.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return 1 + adapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if(isHeader(position)) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isHeader(int posotion) {
        return posotion == 0;
    }

    public void addHeader(View view) {
        this.view = view;
        notifyDataSetChanged();
    }
}
