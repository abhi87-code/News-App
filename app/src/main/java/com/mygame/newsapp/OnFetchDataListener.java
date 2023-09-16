package com.mygame.newsapp;

import com.mygame.newsapp.Model.NewsHeadline;

import java.util.List;

public interface OnFetchDataListener<api_Response> {
     default void OnFetchData(List<NewsHeadline> name, String message) {

     }

     void onError(String message);
}
