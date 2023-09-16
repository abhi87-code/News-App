package com.mygame.newsapp;

import static com.mygame.newsapp.R.id.headline_image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.lights.LightState;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mygame.newsapp.Model.NewsHeadline;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadline> headlines;
    private SelectListener listener;

    public CustomAdapter(Context context, List<NewsHeadline> headlines,SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener=listener;
    }

    @NonNull
    @Override

    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.headline_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NewsHeadline headline = headlines.get(position);

        holder.title.setText(headline.getTitle());
        holder.source.setText(headline.getSource().getName());

        if (headline.getUrlToImage() != null) {
            Picasso.get().load(headline.getUrlToImage()).into(holder.image_headline);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnNewsClicked(headlines.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return headlines != null ? headlines.size() : 0; // Avoid NPE by checking for null
    }
}
