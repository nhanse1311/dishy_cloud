package com.example.dishycloud.repositories;

import android.content.Context;

import com.example.dishycloud.models.Chef;
import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.ReponseData;
import com.example.dishycloud.models.User;
import com.example.dishycloud.utils.CallBackData;

import java.util.List;

public interface DishyRepository {

    void login(String username, String password, Context context, CallBackData<User> callBackData);

    void getUser(String token, CallBackData<User> callBackData);

    void postRecipe(String token, CallBackData<Recipe> callBackData);

    void register(String username, String password, String fullname, Context context, CallBackData<User> callBackData);

    void getAllRecipeSuggestion(String token, CallBackData<List<Recipe>> callBackData);

    void getAllRecipeTop(String token, CallBackData<List<Recipe>> callBackData);

    void getAllRecipeHistory(String token, CallBackData<List<Recipe>> callBackData);

    void getTopChef(String token, CallBackData<List<User>> callBackData);

    void getFollower(String token, CallBackData<List<User>> callBackData);
}
