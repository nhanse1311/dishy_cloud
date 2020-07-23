package com.example.dishycloud.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dishycloud.R;
import com.example.dishycloud.activities.EditAccountActivity;
import com.example.dishycloud.activities.HomeActivity;
import com.example.dishycloud.activities.SignInActivity;
import com.example.dishycloud.activities.SplashActivity;
import com.example.dishycloud.adaptes.AlbumAdapter;
import com.example.dishycloud.adaptes.FollowChefApdater;
import com.example.dishycloud.adaptes.RecipeConcernAdapter;
import com.example.dishycloud.models.Chef;
import com.example.dishycloud.models.ItemsQuickSearch;
import com.example.dishycloud.models.User;
import com.example.dishycloud.presenters.GetFollowerPresenter;
import com.example.dishycloud.presenters.GetUserPresenter;
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.views.GetFollowerView;
import com.example.dishycloud.views.GetInfoVIew;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends Fragment implements View.OnClickListener, GetInfoVIew<User>, GetFollowerView<User> {
    private View mView;
    private RecyclerView mRecycleView, mRecycleViewConcern;
    private FollowChefApdater mFollowAdapter;
    private AlbumAdapter albumAdapter;
    private RecipeConcernAdapter mRecipeConcernAdapter;
    private List<Chef> items;
    private List<ItemsQuickSearch> quickSearchList;
    private ImageView mTxtEditAccount, mImgAvatar;
    private Button mBtnLogout;
    private DatabaseHelper mDatabaseHelper;
    private GetUserPresenter mGetUserPresenter;
    private GetFollowerPresenter mGetFollowerPresenter;
    private TextView mTxtFullname;
    private TextView mTxtFollower;
    private TextView mTxtRecipe;

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_user, container, false);
        initView();
        initData();
        return mView;
    }

    private void initView() {
        mRecycleView = mView.findViewById(R.id.rcv_follow);
        mRecycleViewConcern = mView.findViewById(R.id.rcv_concern);
        mTxtEditAccount = mView.findViewById(R.id.txt_update_account);
        mImgAvatar = mView.findViewById(R.id.imgAvatar_uf);
        mBtnLogout = mView.findViewById(R.id.btn_logout);
        mTxtFullname = mView.findViewById(R.id.txt_ufm_fullname);
        mTxtFollower = mView.findViewById(R.id.txt_ufm_followerr);
        mTxtRecipe = mView.findViewById(R.id.txt_ufm_recipe);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManagerConcern = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mRecycleViewConcern.setLayoutManager(linearLayoutManagerConcern);
    }

    private void initData() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        // get userinfor
        mGetUserPresenter =  new GetUserPresenter(this);
        mGetUserPresenter.getUser(mDatabaseHelper.getToken());
        // list follower
        mGetFollowerPresenter = new GetFollowerPresenter(this);
        mGetFollowerPresenter.getFollower(mDatabaseHelper.getToken());

        mTxtEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mView.getContext(), EditAccountActivity.class);
                startActivity(intent);
            }
        });

        mBtnLogout.setOnClickListener(this);
    }

    private void updateRcv(List<User> listUser) {
        if (mFollowAdapter == null) {
            mFollowAdapter = new FollowChefApdater(getContext(), listUser);
            mRecycleView.setAdapter(mFollowAdapter);
        } else {
            mFollowAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_logout:
                mDatabaseHelper.deleteToken();
                Intent intent =  new Intent(getContext(), SignInActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }

    @Override
    public void onSuccess(User user) {
        mTxtFullname.setText(user.getFullname());
        mTxtFollower.setText("Có "+user.getNumberFollower()+" theo dõi");
        mTxtRecipe.setText("Có "+user.getNumberRecipe()+" công thức");
        Picasso.Builder builder = new Picasso.Builder(getContext());
        builder.build().load(user.getAvartar())
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(mImgAvatar);
    }

    @Override
    public void onSuccess(List<User> chefList) {
        updateRcv(chefList);
    }

    @Override
    public void onFail(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }
}
