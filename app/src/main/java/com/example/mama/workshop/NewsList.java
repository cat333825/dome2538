package com.example.mama.workshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mama.workshop.entity.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewsList extends AppCompatActivity {

    private ListView lvMenu;

    static String[] NewsName = {
            "Topic News",
            "Topic News",
            "Topic News",
            "Topic News",
            "Topic News",
            "Topic News"};
    static String[] DateName = {
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559",
            "31 ตุลาคม 2559"};
    int[] resId = {
            R.drawable.and,
            R.drawable.and,
            R.drawable.and,
            R.drawable.and,
            R.drawable.and,
            R.drawable.and};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        lvMenu = (ListView) findViewById(R.id.lvMenu);
        getNews();
        initListview();
    }

    private void initListview() {

    }

    private void getNews() {
        new RequestNews().execute();
    }


    private class RequestNews extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request;
            Response response;

            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/feednews.php")
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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject rootObj = new JSONObject(s);
                if (rootObj.has("result")) {
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if (resultObj.getInt("result") == 1) {
                        JSONArray newsJsonArr = rootObj.getJSONArray("news_list");
                        if (newsJsonArr != null && newsJsonArr.length() > 0) {
                            int sizeNews = newsJsonArr.length();
                            final List<News> mNewsList = new ArrayList<>();
                            for (int i = 0; i < sizeNews; i++) {
                                JSONObject newsJsonObj = newsJsonArr.getJSONObject(i);
                                News news = new News();
                                news.setNewId(newsJsonObj.getString("news_id"));
                                news.setTitle(newsJsonObj.getString("title"));
                                news.setCreateDate(newsJsonObj.getString("create_date"));
                                news.setShortDescription(newsJsonObj.getString("short_description"));
                                news.setImageUrl(newsJsonObj.getString("image_url"));
                                mNewsList.add(news);
                            }
                            lvMenu.setAdapter(new CustomAdapter(getApplicationContext(),mNewsList));
                            lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(NewsList.this,NewsDetial.class);
                                    intent.putExtra("id",mNewsList.get(i).getNewId()); //การส่งค่าไปหน้า details
                                    startActivity(intent);
                                }
                            });
                        }
                    } else {

                    }
                }

            } catch (JSONException ex) {

            }
        }

    }
}


