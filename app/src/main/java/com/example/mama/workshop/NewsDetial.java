package com.example.mama.workshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.example.mama.workshop.entity.News;
import com.example.mama.workshop.entity.ResponseNews;
import com.example.mama.workshop.entity.Result;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsDetial extends AppCompatActivity {

    private TextView tvNewsDetail;
    private TextView tvTitle;
    private ImageView imgDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detial);
        imgDetail = (ImageView)findViewById(R.id.imgDetail);
        tvNewsDetail = (TextView)findViewById(R.id.tvNewsDetail);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        new RequestNewsDetail(id).execute();
    }

    private class RequestNewsDetail extends AsyncTask<Void, Void, String> {
        String id;

        public RequestNewsDetail(String id) {
            this.id = id;
        }

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request;
            Response response;

            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/getNewsDetail.php?news_id=" + id)
                    .get().build();
            try {
                response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ResponseNews responseNews = new Gson().fromJson(s,ResponseNews.class);
            if(responseNews != null){
                Result result = responseNews.getResult();
                if (result.getResult() == 1){
                    News news  = responseNews.getNews();
                    tvTitle.setText(news.getDetail());
                    tvNewsDetail.setText(news.getTitle());

                    Glide.with(NewsDetial.this).load(news.getImageUrl()).into(imgDetail);


                }
            }

        }
    }
}
