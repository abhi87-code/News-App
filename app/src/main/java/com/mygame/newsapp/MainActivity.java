package com.mygame.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mygame.newsapp.Model.NewsHeadline;
import com.mygame.newsapp.Model.api_Response;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener{

    RecyclerView recyclerView,rv;
    CustomAdapter adapter;

    ProgressDialog dialog;
    List<NewsHeadline> list = new ArrayList<>();
    Button b1,b2,b3,b4,b5,b6,b7;
    SearchView searchView;
    TextView Readmore;


    private final OnFetchDataListener<api_Response> listner=new OnFetchDataListener<api_Response>() {
        @Override
        public void OnFetchData(List<NewsHeadline> list1, String message) {
            if (list1 != null) {
                dialog.dismiss();
                Log.d("Data check", "Data is received correctly " + list1.size());

                list.clear();
                list.addAll(0,list1);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MainActivity.this, "No Data Found!!", Toast.LENGTH_SHORT).show();

                Log.d("Data check", "New list is null");
            }
        }

        @Override
        public void onError(String message) {
            //listner.onError("Error Occured");
            Toast.makeText(MainActivity.this, "API_ERROR Occured!!", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog=new ProgressDialog(this);
        dialog.setTitle("Fetching News Articles...");
        dialog.show();

        searchView=findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching Article of "+query);
                dialog.show();
                Reqmanager reqmanager=new Reqmanager(MainActivity.this);
                reqmanager.getNewsHeadlines(listner,"technology",query,50);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    // Clear the search query and fetch all news articles again
                    dialog.setTitle("Fetching All News Articles");
                    dialog.show();
                    Reqmanager reqmanager = new Reqmanager(MainActivity.this);
                    reqmanager.getNewsHeadlines(listner, "technology", null,50);
                }
                return false;
            }
        });

        b1=findViewById(R.id.Button_business);
        b1.setOnClickListener(this);
        b2=findViewById(R.id.Button_entertainment);
        b2.setOnClickListener(this);
        b3=findViewById(R.id.Button_general);
        b3.setOnClickListener(this);
        b4=findViewById(R.id.Button_health);
        b4.setOnClickListener(this);
        b5=findViewById(R.id.Button_science);
        b5.setOnClickListener(this);
        b6=findViewById(R.id.Button_sports);
        b6.setOnClickListener(this);
        b7=findViewById(R.id.Button_technology);
        b7.setOnClickListener(this);

        Reqmanager reqmanager=new Reqmanager(this);
        reqmanager.getNewsHeadlines(listner,"technology",null,50);

        recyclerView = findViewById(R.id.recycler_main);
        adapter = new CustomAdapter(this, list,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

    }



    @Override
    public void OnNewsClicked(NewsHeadline headline) {
        startActivity(new Intent(MainActivity.this,DetailActivity.class)
                .putExtra("data", headline));
    }

    @Override
    public void onClick(View view) {
        Log.d("Button Click", "Button clicked: " + view.getId());
    Button button=(Button) view;
    String category =button.getText().toString();
        Log.d("category check ", "category is :"+category);
    dialog.setTitle("Fetching news Article of "+category);
    dialog.show();
    Reqmanager reqmanager=new Reqmanager(this);
    reqmanager.getNewsHeadlines(listner,category,null,50);

    }
}