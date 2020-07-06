package com.example.dishycloud.presenters;

import android.content.Context;

import com.example.dishycloud.models.User;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.RegisterView;

public class RegisterPresenter {
    private Context mContext;
    private RegisterView mRegisterView;
    private DishyRepository mDishyReposiry;

    public RegisterPresenter(Context mContext, RegisterView mRegisterView) {
        this.mContext = mContext;
        this.mRegisterView = mRegisterView;
        mDishyReposiry = new DishyRepositoryImp();
    }

    public void registerAccount(String username, String password, String fullname) {
        mDishyReposiry.register(username, password, fullname, mContext, new CallBackData<User>() {
            @Override
            public void onSucess(User user) {
                mRegisterView.onRegisterSuccess();
            }

            @Override
            public void onFail(String message) {
                mRegisterView.onRegisterFail(message);
            }
        });
    }
}
