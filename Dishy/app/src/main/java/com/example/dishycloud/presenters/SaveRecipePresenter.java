package com.example.dishycloud.presenters;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.SaveRecipeView;

public class SaveRecipePresenter {
    private SaveRecipeView mSaveRecipeView;
    private DishyRepository mDishyRepository;

    public SaveRecipePresenter(SaveRecipeView mSaveRecipeView) {
        this.mSaveRecipeView = mSaveRecipeView;
        this.mDishyRepository = new DishyRepositoryImp();
    }
    public void saveRecipe(String token, String id){
        mDishyRepository.saveRecipe(token, id, new CallBackData<Recipe>() {
            @Override
            public void onSucess(Recipe recipe) {
                mSaveRecipeView.onPutRecipeSuccess(recipe);
            }

            @Override
            public void onFail(String message) {
                mSaveRecipeView.onPutRecipeFail(message);
            }
        });
    }
}
