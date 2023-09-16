package com.mygame.newsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView title,source;
    ImageView image_headline;
    CardView cardView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.title);
        source=itemView.findViewById(R.id.source);
        image_headline=itemView.findViewById(R.id.headline_image);
        cardView=itemView.findViewById(R.id.card);
       // Newsurl=itemView.findViewById(R.id.Readmore);

    }
}
