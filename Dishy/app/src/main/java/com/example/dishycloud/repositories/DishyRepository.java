package com.example.dishycloud.repositories;

import android.content.Context;

import com.example.dishycloud.models.ReponseData;
import com.example.dishycloud.models.User;
import com.example.dishycloud.utils.CallBackData;

public interface DishyRepository {

    void login(String username, String password, Context context, CallBackData<User> callBackData);
    void register(String username, String password, String fullname, Context context, CallBackData<User> callBackData);
}
