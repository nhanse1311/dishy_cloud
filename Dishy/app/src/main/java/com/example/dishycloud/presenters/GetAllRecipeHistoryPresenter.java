package com.example.dishycloud.presenters;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.GetAllRecipeHistoryView;

import java.util.List;

public class GetAllRecipeHistoryPresenter {
    private GetAllRecipeHistoryView<Recipe> mRecipeHistoryView;
    private DishyRepository mDishyRepository;

    public GetAllRecipeHistoryPresenter(GetAllRecipeHistoryView<Recipe> mRecipeHistoryView) {
        this.mRecipeHistoryView = mRecipeHistoryView;
        this.mDishyRepository = new DishyRepositoryImp();
    }

    public void getAllRecipeHistory(String token){
        mDishyRepository.getAllRecipeHistory(token, new CallBackData<List<Recipe>>() {
            @Override
            public void onSucess(List<Recipe> recipeList) {
                mRecipeHistoryView.onSuccess(recipeList);
            }

            @Override
            public void onFail(String message) {
                mRecipeHistoryView.onFail(message);
            }
        });
    }
}
