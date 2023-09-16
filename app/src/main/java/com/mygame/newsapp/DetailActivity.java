package com.mygame.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygame.newsapp.Model.NewsHeadline;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    NewsHeadline headline;
    TextView txt_title,txt_author,txt_time,txt_details,txt_content,readmore;
    ImageView img_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txt_title=findViewById(R.id.DetailTextTitle);

        txt_author=findViewById(R.id.DetailTextAuthor);
        readmore=findViewById(R.id.Readmore);
        txt_time=findViewById(R.id.DetailTextTime);
        txt_details=findViewById(R.id.DetailTextdetails);
        txt_content=findViewById(R.id.DetailTextcontent);
        img_news=findViewById(R.id.detail_img);

        headline= (NewsHeadline) getIntent().getSerializableExtra("data");

        txt_title.setText(headline.getTitle());
        txt_details.setText(headline.getDescription());
        txt_content.setText(headline.getContent());
        txt_author.setText(headline.getAuthor());
        txt_time.setText(headline.getPublishedAt());
        Picasso.get().load(headline.getUrlToImage()).into(img_news);
        readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sourceUrl = headline.getUrl();
                if (sourceUrl != null && !sourceUrl.isEmpty()) {
                    // Open the URL in a web browser or WebView
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sourceUrl));
                    startActivity(intent);
                }
            }
        });
    }
}