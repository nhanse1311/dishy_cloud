package com.example.dishycloud.presenters;

import android.content.Context;

import com.example.dishycloud.models.User;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.LoginView;

public class LoginPresenter {
    private Context mContext;
    private LoginView mLoginView;
    private DishyRepository mDishyRepository;
    private DatabaseHelper mDatabaseHelper;

    public LoginPresenter(Context mContext, LoginView mLoginView) {
        this.mContext = mContext;
        this.mLoginView = mLoginView;
        mDishyRepository = new DishyRepositoryImp();
    }

    public void onLogin(String username, String password){
        mDishyRepository.login(username, password, mContext, new CallBackData<User>() {
            @Override
            public void onSucess(User user) {
                mDatabaseHelper = new DatabaseHelper(mContext);
                mDatabaseHelper.addNote(user);
                mLoginView.onLoginSuccess();
            }

            @Override
            public void onFail(String message) {
                mLoginView.onLoginFail(message);
            }
        });
    }
}
