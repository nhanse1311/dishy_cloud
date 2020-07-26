package com.example.dishycloud.presenters;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.GetAllRecipeSuggestionView;

import java.util.List;

public class GetAllRecipeTopPresenter {
    private GetAllRecipeSuggestionView<Recipe> mGetAllRecipeSuggestionView;
    private DishyRepository mDishyRepository;

    public GetAllRecipeTopPresenter(GetAllRecipeSuggestionView<Recipe> mRecipeTopView) {
        this.mGetAllRecipeSuggestionView = mRecipeTopView;
        this.mDishyRepository = new DishyRepositoryImp();
    }

    public void getAllRecipeTop(String token) {
        mDishyRepository.getAllRecipeTop(token, new CallBackData<List<Recipe>>() {
            @Override
            public void onSucess(List<Recipe> recipeList) {
                mGetAllRecipeSuggestionView.onRecipeSuggestionSuccess(recipeList);
            }

            @Override
            public void onFail(String message) {
                mGetAllRecipeSuggestionView.onFail(message);
            }
        });
    }
}
