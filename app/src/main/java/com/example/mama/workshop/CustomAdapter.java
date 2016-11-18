package com.example.mama.workshop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mama.workshop.entity.News;

import java.util.List;

/**
 * Created by Mama on 11/4/2016.
 */

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    List<News>data;


    public CustomAdapter(Context context,List<News>data) {
        this.mContext = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder myHolder = null;

        if (convertView == null) {
            //คือช่วงแรกๆที่ convertView ยังไม่มีค่า

            //เตรียม Layout ที่ต้องการ ให้กับ convertView
            convertView = mInflater.inflate(R.layout.activity_row_list, null);

            myHolder = new ViewHolder();
            myHolder.tvNews = (TextView) convertView.findViewById(R.id.tvNews);
            myHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            myHolder.imgAnd = (ImageView) convertView.findViewById(R.id.imgAnd);
            convertView.setTag(myHolder);
        } else {
            //คือช่วงที่ convertView ผ่านการ Scroll มาแล้ว
            myHolder = (ViewHolder) convertView.getTag();
        }

        // ต้อง set ค่าที่จะให้แสดงผลกับแต่ละ widget
        News news = data.get(position);
        myHolder.tvNews.setText(news.getTitle());
        myHolder.tvDate.setText(news.getCreateDate());
        Glide.with(mContext).load(news.getImageUrl()).into(myHolder.imgAnd);
        //myHolder.imgAnd.setImageResource(news.getImageUrl());



        return convertView;
    }

    public class ViewHolder {
        //ประกาษว่าภายใน view จะมี widget อะไร ชื่ออะไรบ้าง
        TextView tvNews;
        TextView tvDate;
        ImageView imgAnd;

    }

}
