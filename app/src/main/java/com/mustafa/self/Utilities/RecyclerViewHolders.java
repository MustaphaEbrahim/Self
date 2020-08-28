package com.mustafa.self.Utilities;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.self.databinding.JournalRowBinding;

/**
 * Created by Youssif Hamdy on 12/8/2019.
 */
public class RecyclerViewHolders {


    public static class JournalsViewHolder extends RecyclerView.ViewHolder {
        public TextView
                title,
                thoughts,
                name,
                dateAdded;
        public ImageView image;
        public ImageButton shareButton;


        public JournalsViewHolder(JournalRowBinding itmBinding) {
            super(itmBinding.getRoot());

            title = itmBinding.journalTitleList;
            thoughts = itmBinding.journalThoughtList;
            dateAdded = itmBinding.journalTimestampList;
            image = itmBinding.journalImageList;
            name = itmBinding.journalRowUsername;
            shareButton = itmBinding.journalRowShareButton;


        }
    }

}
