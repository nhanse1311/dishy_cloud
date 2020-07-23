package com.example.dishycloud.presenters.follower;

import android.content.Context;

import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.UnFollowerView;

public class UnFollowerPresenter {
    private Context mContext;
    private UnFollowerView mUnFollowerView;
    private DishyRepository mDishyRepository;

    public UnFollowerPresenter(Context mContext, UnFollowerView mUnFollowerView) {
        this.mContext = mContext;
        this.mUnFollowerView = mUnFollowerView;
        this.mDishyRepository = new DishyRepositoryImp();
    }

    public void unFollow(String token, String username, String followe){
        mDishyRepository.unFollow(token, username, followe, new CallBackData<String>() {
            @Override
            public void onSucess(String s) {
                mUnFollowerView.onUnFollowSuccess();
            }

            @Override
            public void onFail(String message) {
                mUnFollowerView.onUnFollowFail(message);
            }
        });
    }
}
