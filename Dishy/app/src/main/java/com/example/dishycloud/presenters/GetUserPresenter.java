package com.example.dishycloud.presenters;

import com.example.dishycloud.models.User;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.GetInfoVIew;
import com.example.dishycloud.views.LoginView;

public class GetUserPresenter {
    private GetInfoVIew<User> mLoginView;
    private DishyRepository mDishyRepository;

    public GetUserPresenter(GetInfoVIew<User> mLoginView) {
        this.mLoginView = mLoginView;
        this.mDishyRepository = new DishyRepositoryImp();
    }

    public void getUser(String token){
        mDishyRepository.getUser(token, new CallBackData<User>() {
            @Override
            public void onSucess(User user) {
                mLoginView.onSuccess(user);
            }

            @Override
            public void onFail(String message) {
                mLoginView.onFail(message);
            }
        });
    }
}
