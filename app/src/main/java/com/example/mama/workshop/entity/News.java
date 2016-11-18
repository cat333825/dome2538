package com.example.mama.workshop.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mama on 11/12/2016.
 */

public class News {
    @SerializedName("news_id")
    private String newId;
    @SerializedName("title")
    private String title;
    @SerializedName("create_date")
    private String createDate;
    @SerializedName("short_description")
    private String shortDescription;
    @SerializedName("image_url")
    private String imageUrl;

    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
