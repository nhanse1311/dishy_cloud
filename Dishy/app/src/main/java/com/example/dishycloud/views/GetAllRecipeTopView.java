package com.example.dishycloud.views;

import com.example.dishycloud.models.Recipe;

import java.util.List;

public interface GetAllRecipeTopView<T> {
    void onRecipeTopSuccess(List<Recipe> recipeList);
    void onFail(String message);
}
