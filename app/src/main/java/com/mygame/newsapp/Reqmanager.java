package com.mygame.newsapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.mygame.newsapp.Model.api_Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Reqmanager {

    Context context;

          Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void getNewsHeadlines(OnFetchDataListener listener, String category, String query,int pageSize){
        //String cnt=context.getString(R.string.India);
        CallNewsApi callNewsApi =retrofit.create(CallNewsApi.class);
        Call<api_Response> call= callNewsApi.callHeadlines("in", category,query,pageSize,context.getString(R.string.Api_Key));
            try{
            call.enqueue(new Callback<api_Response>() {
                @Override
                public void onResponse(Call<api_Response> call, Response<api_Response> response) {
                    if (!response.isSuccessful()) {
                        Log.e("API Error", response.message());
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                    } else {
                        listener.OnFetchData(response.body().getArticles(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<api_Response> call, Throwable t) {
                    listener.onError("Request Failed");
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Reqmanager(Context context) {
        this.context = context;
        //this.selectedCountry = selectedCountry;
    }

    public interface CallNewsApi{
        @GET("top-headlines")
        Call<api_Response> callHeadlines(
             @Query("country") String country,
             @Query("category") String category,
             @Query("q") String query,
             @Query("pageSize") int pageSize,
             @Query("apiKey") String api_key
        );
    }
}
