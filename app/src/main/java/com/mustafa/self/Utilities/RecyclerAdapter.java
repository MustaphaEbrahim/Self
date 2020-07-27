package com.mustafa.self.Utilities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Youssif Hamdy on 12/8/2019.
 */
public abstract class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    int ItemsCount;

    public RecyclerAdapter(Context context, int ItemsCount) {
        this.context = context;
        this.ItemsCount = ItemsCount;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView;
        RecyclerView.ViewHolder rcv;
        switch (viewType) {
/*
            case TABLES:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tables_item, parent, false);
                rcv = new RecyclerViewHolders.TablesHolder(layoutView);
                return rcv;*/


        }

        return null;

    }


    @Override
    public abstract void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position);

    @Override
    public abstract int getItemCount();

    public void updateItemCount(int count) {
        ItemsCount = count;
    }


}

