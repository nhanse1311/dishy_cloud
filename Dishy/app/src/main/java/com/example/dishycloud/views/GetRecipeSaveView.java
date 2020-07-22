package com.example.dishycloud.views;

import com.example.dishycloud.models.Recipe;

import java.util.List;

public interface GetRecipeSaveView {
    void onGetRecipeSaveSuccess(List<Recipe> recipes);
    void onGetRecipeSaveFail(String message);
}
