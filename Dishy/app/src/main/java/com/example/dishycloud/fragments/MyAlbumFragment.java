package com.example.dishycloud.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.activities.MyRecipeActivity;
import com.example.dishycloud.adaptes.AlbumAdapter;
import com.example.dishycloud.models.Dishy;
import com.example.dishycloud.models.Material;
import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.StepMake;
import com.example.dishycloud.presenters.GetRecipeAuthPresenter;
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.views.GetRecipeAuthView;

import java.util.ArrayList;
import java.util.List;

public class MyAlbumFragment extends Fragment implements GetRecipeAuthView {

    private View mView;
    private RecyclerView rcv_my_ablum;
    private AlbumAdapter albumAdapter;
    private GetRecipeAuthPresenter mGetRecipeAuthPresenter;
    private DatabaseHelper mDatabaseHelper;

    public static MyAlbumFragment newInstance() {
        MyAlbumFragment myAlbumFragment = new MyAlbumFragment();
        Bundle args = new Bundle();
        myAlbumFragment.setArguments(args);
        return myAlbumFragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my_album, container, false);
        initView();
        initData();
        return mView;
    }

    private void initView() {
        rcv_my_ablum = mView.findViewById(R.id.rcv_my_album);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rcv_my_ablum.setLayoutManager(gridLayoutManager);
    }

    private void initData() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        mGetRecipeAuthPresenter = new GetRecipeAuthPresenter(getContext(), this);
        mGetRecipeAuthPresenter.getRecipeByAuth(mDatabaseHelper.getToken());
    }

    private void updateRcvDishy(List<Recipe> dishies) {
        if (albumAdapter == null) {
            albumAdapter = new AlbumAdapter(getContext(), dishies, true);
            rcv_my_ablum.setAdapter(albumAdapter);
            albumAdapter.setmOnAlbumClickListener(new AlbumAdapter.OnAlbumClickListener() {
                @Override
                public void OnClick(Recipe dishy) {
                    Intent move = new Intent(getContext(), MyRecipeActivity.class);
                    move.putExtra("MYDISHY",dishy);
                    getActivity().startActivityForResult(move,2019);
                }
            });
        } else {
            albumAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetRecipeSucess(List<Recipe> recipes) {
        updateRcvDishy(recipes);
    }

    @Override
    public void onGetRecipeFail(String message) {

    }
}
