package com.example.dishycloud.presenters;

import com.example.dishycloud.models.Chef;
import com.example.dishycloud.models.User;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.GetTopChefView;

import java.util.List;

public class GetTopChefPresenter {
    private GetTopChefView<User> mTopChefView;
    private DishyRepository mDishyRepository;

    public GetTopChefPresenter(GetTopChefView<User> mTopChefView) {
        this.mTopChefView = mTopChefView;
        this.mDishyRepository = new DishyRepositoryImp();
    }

    public void getTopChef(String token) {
        mDishyRepository.getTopChef(token, new CallBackData<List<User>>() {
            @Override
            public void onSucess(List<User> chefList) {
                mTopChefView.onSuccessChef(chefList);
            }

            @Override
            public void onFail(String message) {
                mTopChefView.onFail(message);
            }
        });
    }
}
