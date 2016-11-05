package com.example.mama.workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SimpleListView extends AppCompatActivity {

    private ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_view);

        lvSimple = (ListView) findViewById(R.id.lvSimple);

        String[] valuse = new String[] {"News"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,      //context of this activity
                android.R.layout.simple_list_item_1,
                valuse
        );
        lvSimple.setAdapter(adapter);
    }
}
