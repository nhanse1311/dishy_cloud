package com.example.dishycloud.views;

import com.example.dishycloud.models.Recipe;

public interface SaveRecipeView {
   void onPutRecipeSuccess(Recipe recipe);
   void onPutRecipeFail(String fail);
}
