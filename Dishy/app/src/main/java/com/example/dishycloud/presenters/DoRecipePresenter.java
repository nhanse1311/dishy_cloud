package com.example.dishycloud.presenters;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.DoRecipeView;

public class DoRecipePresenter {
    private DoRecipeView mDoRecipeView;
    private DishyRepository mDishyRepository;

    public DoRecipePresenter(DoRecipeView mDoRecipeView) {
        this.mDoRecipeView = mDoRecipeView;
        mDishyRepository = new DishyRepositoryImp();
    }

    public void doRecipe(String token, String id){
        mDishyRepository.doRecipe(token, id, new CallBackData<String>() {
            @Override
            public void onSucess(String recipe) {
                mDoRecipeView.onDoRecipeSuccess(recipe);
            }

            @Override
            public void onFail(String message) {
                mDoRecipeView.onDoRecipeFail(message);
            }
        });
    }
}
