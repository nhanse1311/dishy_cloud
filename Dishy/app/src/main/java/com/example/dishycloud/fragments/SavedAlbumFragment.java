package com.example.dishycloud.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
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
import com.example.dishycloud.presenters.GetRecipeSavePresenter;
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.views.GetRecipeSaveView;

import java.util.ArrayList;
import java.util.List;

public class SavedAlbumFragment extends Fragment implements GetRecipeSaveView {

    private View mView;
    private RecyclerView rcv_saved_ablum;
    private List<Dishy> dishies;
    private List<Material> mMaterial1;
    private List<StepMake> mMakes;
    private AlbumAdapter albumAdapter;
    private GetRecipeSavePresenter mGetRecipeSavePresenter;
    private DatabaseHelper mDatabaseHelper;

    public static SavedAlbumFragment newInstance() {
        SavedAlbumFragment savedAlbumFragment = new SavedAlbumFragment();
        Bundle args = new Bundle();
        savedAlbumFragment.setArguments(args);
        return savedAlbumFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_saved_album, container, false);
        initView();
        initData();

        return mView;
    }

    private void initView() {
        rcv_saved_ablum = mView.findViewById(R.id.rcv_saved_album);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rcv_saved_ablum.setLayoutManager(gridLayoutManager);
    }

    private void initData() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        mGetRecipeSavePresenter = new GetRecipeSavePresenter(getContext(), this);
        mGetRecipeSavePresenter.getRecipeSave(mDatabaseHelper.getToken());
    }

    private void updateRcvDishy(List<Recipe> dishies) {
        if (albumAdapter == null) {
            albumAdapter = new AlbumAdapter(getContext(), dishies, true);
            rcv_saved_ablum.setAdapter(albumAdapter);
            albumAdapter.setmOnAlbumClickListener(new AlbumAdapter.OnAlbumClickListener() {
                @Override
                public void OnClick(Recipe recipe) {
                    Intent move = new Intent(getContext(), MyRecipeActivity.class);
                    move.putExtra("MYDISHY",recipe);
                    getActivity().startActivityForResult(move,2019);
                }
            });
        } else {
            albumAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetRecipeSaveSuccess(List<Recipe> recipes) {
        updateRcvDishy(recipes);
    }

    @Override
    public void onGetRecipeSaveFail(String message) {

    }
}
