package com.example.dishycloud.views;

import com.example.dishycloud.models.Recipe;

import java.util.List;

public interface GetAllRecipeHistoryView<T> {
    void onSuccess(List<Recipe> recipeList);
    void onFail(String message);
}
