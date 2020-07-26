package com.example.dishycloud.views;

import com.example.dishycloud.models.Recipe;

public interface DoRecipeView {
    void onDoRecipeSuccess(String recipe);
    void onDoRecipeFail(String message);
}
