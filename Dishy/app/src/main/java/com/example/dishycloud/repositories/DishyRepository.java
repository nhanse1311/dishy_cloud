package com.example.dishycloud.repositories;

import android.content.Context;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.ReponseData;
import com.example.dishycloud.models.User;
import com.example.dishycloud.utils.CallBackData;

public interface DishyRepository {

    void login(String username, String password, Context context, CallBackData<User> callBackData);
<<<<<<< HEAD

    void getUser(String token, CallBackData<User> callBackData);

    void postRecipe(String token, CallBackData<Recipe> callBackData);
=======
    void register(String username, String password, String fullname, Context context, CallBackData<User> callBackData);
>>>>>>> e9987c503e38607b70d19ab5ef2f2a750facb7ef
}
