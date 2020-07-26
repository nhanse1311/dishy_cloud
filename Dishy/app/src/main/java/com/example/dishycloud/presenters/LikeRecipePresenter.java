package com.example.dishycloud.presenters;

import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.LikeRecipeView;

public class LikeRecipePresenter {
    private LikeRecipeView mLikeRecipeView;
    private DishyRepository mDishyRepository;

    public LikeRecipePresenter(LikeRecipeView mLikeRecipeView) {
        this.mLikeRecipeView = mLikeRecipeView;
        this.mDishyRepository = new DishyRepositoryImp();
    }

    public void likeRecipe(String token, String id){
        mDishyRepository.likeRecipe(token, id, new CallBackData<String>() {
            @Override
            public void onSucess(String s) {
                mLikeRecipeView.onLikeRecipeSuccess(s);
            }

            @Override
            public void onFail(String message) {
                mLikeRecipeView.onLikeRecipeSuccess(message);
            }
        });
    }
}
