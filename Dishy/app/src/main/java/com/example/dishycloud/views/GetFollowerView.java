package com.example.dishycloud.views;

import com.example.dishycloud.models.User;

import java.util.List;

public interface GetFollowerView<T> {
    void onSuccess(List<User> chefList);
    void onFail(String message);
}
