package com.example.dishycloud.views;

public interface GetInfoVIew<T> {
    void onSuccess(T t);
    void onFail(String message);
}
