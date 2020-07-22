package com.example.dishycloud.repositories;

import android.content.Context;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.ReponseData;
import com.example.dishycloud.models.ResponseGet;
import com.example.dishycloud.models.User;
import com.example.dishycloud.utils.CallBackData;

import java.util.List;

public interface DishyRepository {

    void login(String username, String password, Context context, CallBackData<User> callBackData);
    void getUser(String token, CallBackData<User> callBackData);

    void postRecipe(String token,Recipe recipe, CallBackData<Recipe> callBackData);
    void register(String username, String password, String fullname, Context context, CallBackData<User> callBackData);

    void postImage(Context context,String token,List<String> imagePath ,CallBackData<List<String>> callBackData);
    void getRecipeByAuth(Context context, String token, CallBackData<List<Recipe>> callBackData);
    void getRecipeSave(Context context, String token, CallBackData<List<Recipe>> callBackData);
}
