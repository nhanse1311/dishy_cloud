package com.example.dishycloud.presenters;


import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.ReponseData;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.GetAllRecipeSuggestionView;

import java.util.List;

public class GetAllRecipeSuggestionPresenter {
    private GetAllRecipeSuggestionView<Recipe> mRecipeSuggestionView;
    private DishyRepository mDishyRepository;

    public GetAllRecipeSuggestionPresenter(GetAllRecipeSuggestionView<Recipe> mRecipeSuggestionView) {
        this.mRecipeSuggestionView = mRecipeSuggestionView;
        this.mDishyRepository = new DishyRepositoryImp();
    }

    public void getAllRecipeSuggestion(String token) {
        mDishyRepository.getAllRecipeSuggestion(token, new CallBackData<List<Recipe>>() {

            @Override
            public void onSucess(List<Recipe> listReponseData) {
                mRecipeSuggestionView.onSuccess(listReponseData);
            }

            @Override
            public void onFail(String message) {
                mRecipeSuggestionView.onFail(message);
            }
        });
    }
}
