package com.example.dishycloud.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReponseData<T> implements Serializable {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private T data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
