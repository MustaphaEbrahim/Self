package com.example.self.Utilities;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.self.R;
import com.example.self.databinding.JournalRowBinding;

/**
 * Created by Youssif Hamdy on 12/8/2019.
 */
public class RecyclerViewHolders {


    public static class JournalsViewHolder extends RecyclerView.ViewHolder {
        public TextView
                title,
                thoughts,
                dateAdded;
        public ImageView image;


        public JournalsViewHolder(JournalRowBinding itmBinding) {
            super(itmBinding.getRoot());

            title = itmBinding.journalTitleList;
            thoughts = itmBinding.journalThoughtList;
            dateAdded = itmBinding.journalTimestampList;
            image = itmBinding.journalImageList;


        }
    }

}
