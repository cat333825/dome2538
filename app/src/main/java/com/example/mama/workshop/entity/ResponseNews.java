package com.example.mama.workshop.entity;

/**
 * Created by Mama on 11/12/2016.
 */

public class ResponseNews {
    Result result;
    News news;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
