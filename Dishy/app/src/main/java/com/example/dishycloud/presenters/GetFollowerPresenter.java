package com.example.dishycloud.presenters;

import com.example.dishycloud.models.User;
import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.GetFollowerView;

import java.util.List;

public class GetFollowerPresenter {
    private GetFollowerView<User> mGetFollowerView;
    private DishyRepository mDishyRepository;

    public GetFollowerPresenter(GetFollowerView<User> mGetFollowerView) {
        this.mGetFollowerView = mGetFollowerView;
        this.mDishyRepository = new DishyRepositoryImp();
    }

    public void getFollower(String token) {
        mDishyRepository.getFollower(token, new CallBackData<List<User>>() {
            @Override
            public void onSucess(List<User> userList) {
                mGetFollowerView.onSuccess(userList);
            }

            @Override
            public void onFail(String message) {
                mGetFollowerView.onFail(message);
            }
        });
    }
}
