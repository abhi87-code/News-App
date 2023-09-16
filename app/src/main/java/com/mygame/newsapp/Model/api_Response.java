package com.mygame.newsapp.Model;

import java.io.Serializable;
import java.util.List;

public class api_Response implements Serializable {
    String status;
    int totalResult;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<NewsHeadline> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsHeadline> articles) {
        this.articles = articles;
    }

    List<NewsHeadline> articles;
}
