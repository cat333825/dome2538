package com.example.mama.workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class NewsDetial extends AppCompatActivity {

    static String[] TopiNews = {"Top News"};
    static String[] Date = {"31 ตุลาคม 2559"};
    int[] resId = {R.drawable.and};

    private android.widget.ListView lvMenu1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        lvMenu1 = (android.widget.ListView) findViewById(R.id.lvMenu);
    }
}
