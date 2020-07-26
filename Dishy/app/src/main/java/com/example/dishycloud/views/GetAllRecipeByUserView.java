package com.example.dishycloud.views;

import com.example.dishycloud.models.Recipe;

import java.util.List;

public interface GetAllRecipeByUserView{
    void onSuccessByUser(List<Recipe> recipeList);
    void onFailByUser(String message);
}
