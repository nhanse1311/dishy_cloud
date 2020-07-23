package com.example.dishycloud.presenters.follower;

import android.content.Context;

import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.AddFollowerView;

public class AddFollowerPresenter {
    private Context mContext;
    private AddFollowerView mAddFollowerView;
    private DishyRepository mDishyRepository;

    public AddFollowerPresenter(Context mContext, AddFollowerView mAddFollowerView) {
        this.mContext = mContext;
        this.mAddFollowerView = mAddFollowerView;
        this.mDishyRepository = new DishyRepositoryImp();
    }

    public void addFollower(String token, String usernmae, String follower){
        mDishyRepository.addFollow(token, usernmae, follower, new CallBackData<String>() {
            @Override
            public void onSucess(String s) {
                mAddFollowerView.onAddFollowerSuccess();
            }

            @Override
            public void onFail(String message) {
                mAddFollowerView.onAddFollowerFail(message);
            }
        });
    }
}
