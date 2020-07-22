package com.example.dishycloud.presenters;

import android.content.Context;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.ReponseData;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.GetRecipeAuthView;

import java.util.List;

public class GetRecipeAuthPresenter {
    private Context mContext;
    private GetRecipeAuthView mGetRecipeAuthView;
    private DishyRepository mDishyRepository;

    public GetRecipeAuthPresenter(Context mContext, GetRecipeAuthView mGetRecipeAuthView) {
        this.mContext = mContext;
        this.mGetRecipeAuthView = mGetRecipeAuthView;
        mDishyRepository = new DishyRepositoryImp();
    }

    public void getRecipeByAuth(String token){
        mDishyRepository.getRecipeByAuth(mContext, token, new CallBackData<List<Recipe>>() {
            @Override
            public void onSucess(List<Recipe> recipeList) {
                mGetRecipeAuthView.onGetRecipeSucess(recipeList);
            }

            @Override
            public void onFail(String message) {
                mGetRecipeAuthView.onGetRecipeFail(message);
            }
        });
    }
}
