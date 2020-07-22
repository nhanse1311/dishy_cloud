package com.example.dishycloud.presenters;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.CreateRecipeView;

public class CreateRecipePresenter {
    private CreateRecipeView mCreateRecipeView;
    private DishyRepository mDishyRepository;

    public CreateRecipePresenter(CreateRecipeView mCreateRecipeView) {
        this.mCreateRecipeView = mCreateRecipeView;
        this.mDishyRepository = new DishyRepositoryImp();
    }

    public void createRecipe(String token, Recipe recipe){
        mDishyRepository.postRecipe(token,recipe, new CallBackData<Recipe>() {
            @Override
            public void onSucess(Recipe recipe) {
                mCreateRecipeView.onSuccess();
            }

            @Override
            public void onFail(String message) {
                mCreateRecipeView.onFail(message);
            }
        });
    }
}
