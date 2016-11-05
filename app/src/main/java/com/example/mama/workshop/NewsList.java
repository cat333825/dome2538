package com.example.mama.workshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
        lvMenu.setAdapter(new CustomAdapter(getApplicationContext(), NewsName,DateName, resId));


    }
}


