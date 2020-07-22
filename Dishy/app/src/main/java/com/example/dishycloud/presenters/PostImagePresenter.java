package com.example.dishycloud.presenters;

import android.content.Context;

import com.example.dishycloud.repositories.DishyRepository;
import com.example.dishycloud.repositories.DishyRepositoryImp;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.views.PostImageView;

import java.util.List;

public class PostImagePresenter {
    private Context context;
    private PostImageView mPostImageView;
    private DishyRepository mDishyRepository;

    public PostImagePresenter(Context context, PostImageView mPostImageView) {
        this.context = context;
        this.mPostImageView = mPostImageView;
        mDishyRepository = new DishyRepositoryImp();
    }
    public void postImage( String token, List<String> imagePaths){
        mDishyRepository.postImage(context, token,imagePaths ,new CallBackData<List<String>>() {
            @Override
            public void onSucess(List<String> list) {
                mPostImageView.onPostImageSucces(list);
            }

            @Override
            public void onFail(String message) {
                mPostImageView.onPostImageFail(message);
            }
        });
    }
}
