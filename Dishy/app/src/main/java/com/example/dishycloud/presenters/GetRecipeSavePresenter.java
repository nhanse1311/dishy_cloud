package com.example.dishycloud.presenters;

import android.content.Context;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.GetRecipeSaveView;

import java.util.List;

public class GetRecipeSavePresenter {
    private Context mContext;
    private GetRecipeSaveView mGetRecipeSaveView;
    private DishyRepository mDishyRepository;

    public GetRecipeSavePresenter(Context mContext, GetRecipeSaveView mGetRecipeSaveView) {
        this.mContext = mContext;
        this.mGetRecipeSaveView = mGetRecipeSaveView;
        mDishyRepository = new DishyRepositoryImp();
    }

    public void getRecipeSave(String token){
        mDishyRepository.getRecipeSave(mContext, token, new CallBackData<List<Recipe>>() {
            @Override
            public void onSucess(List<Recipe> recipes) {
                mGetRecipeSaveView.onGetRecipeSaveSuccess(recipes);
            }

            @Override
            public void onFail(String message) {
                mGetRecipeSaveView.onGetRecipeSaveFail(message);
            }
        });
    }
}
