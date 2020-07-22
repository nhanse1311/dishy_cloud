package com.example.dishycloud.views;

import com.example.dishycloud.models.Chef;
import com.example.dishycloud.models.User;

import java.util.List;

public interface GetTopChefView<T> {
    void onSuccessChef(List<User> chefList);
    void onFail(String message);
}
