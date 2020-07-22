package com.example.dishycloud.views;

import com.example.dishycloud.models.Recipe;

import java.util.List;

public interface GetRecipeAuthView {
    void onGetRecipeSucess(List<Recipe>recipes);
    void onGetRecipeFail(String message);
}
