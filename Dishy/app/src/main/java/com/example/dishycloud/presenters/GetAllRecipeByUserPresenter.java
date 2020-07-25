package com.example.dishycloud.presenters;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.GetAllRecipeByUserView;

import java.util.List;

public class GetAllRecipeByUserPresenter {
    private GetAllRecipeByUserView mRecipeByUserView;
    private DishyRepository mDishyRepository;

    public GetAllRecipeByUserPresenter(GetAllRecipeByUserView mRecipeByUserView) {
        this.mRecipeByUserView = mRecipeByUserView;
        this.mDishyRepository = new DishyRepositoryImp();
    }
    public void getAllRecipeByUser(String token, String username){
        mDishyRepository.getAllRecipeByUser(token, username, new CallBackData<List<Recipe>>() {
            @Override
            public void onSucess(List<Recipe> recipeList) {
                mRecipeByUserView.onSuccess(recipeList);
            }

            @Override
            public void onFail(String message) {
                mRecipeByUserView.onFail(message);
            }
        });
    }
}
