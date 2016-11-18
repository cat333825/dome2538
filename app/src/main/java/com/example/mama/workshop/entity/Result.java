package com.example.mama.workshop.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mama on 11/12/2016.
 */

public class Result {
    @SerializedName("result")
    int result;
    @SerializedName("result_desc")
    String resultDesc;

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
