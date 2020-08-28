package com.mustafa.self.Adapters;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.self.R;
import com.mustafa.self.UI.PostJournal.View.Journal;
import com.mustafa.self.Utilities.RecyclerViewHolders;
import com.mustafa.self.databinding.JournalRowBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterJournals extends RecyclerView.Adapter<RecyclerViewHolders.JournalsViewHolder> {

    private List<Journal> journalList;

    public AdapterJournals(List<Journal> journalList) {
        this.journalList = journalList;
    }

    @NonNull
    @Override
    public RecyclerViewHolders.JournalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolders.JournalsViewHolder(JournalRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolders.JournalsViewHolder holder, int position) {

        String imageUrl;

        holder.title.setText( journalList.get(position).getTitle());
        holder.thoughts.setText( journalList.get(position).getThought());
        holder.name.setText(journalList.get(position).getUserName());
        imageUrl =  journalList.get(position).getImageUrl();
        //1 hour ago..
        //Source: https://medium.com/@shaktisinh/time-a-go-in-android-8bad8b171f87
        String timeAgo = (String) DateUtils.getRelativeTimeSpanString( journalList.get(position)
                .getTimeAdded()
                .getSeconds() * 1000);
        holder.dateAdded.setText(timeAgo);


        /*
         Use Picasso library to download and show image
         */
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.cover)
                .fit()
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }
}
