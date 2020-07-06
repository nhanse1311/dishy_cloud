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
import com.example.dishycloud.presenters.GetUserPresenter;
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.views.GetInfoVIew;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends Fragment implements View.OnClickListener, GetInfoVIew<User> {
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
        mGetUserPresenter =  new GetUserPresenter(this);
        mGetUserPresenter.getUser(mDatabaseHelper.getToken());

        if (items == null) {
            items = new ArrayList<Chef>();
        }

        Chef chef1 = new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-1/p320x320/61090498_1285841494925963_1183091008456359936_n.jpg?_nc_cat=101&_nc_oc=AQkXDsimHahDSzxF7BS9NbBvgox8P-BAyPNh2DvJlOZdkZqhBm3KS206w5f7cw1PBneiFi6EtydeF5Gf1avxxUxS&_nc_ht=scontent.fsgn5-1.fna&oh=5092122e39724a586043169cf47d0696&oe=5E2FC72F","Nguyễn Thanh Nhàn",100,1000,null);
        Chef chef3 = new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/c0.0.320.320a/p320x320/10599638_1504520149789789_4487427289442049165_n.jpg?_nc_cat=107&_nc_oc=AQne8gyZOiKbnc5zvKNLPtE9JeuC3flPcvR_za52mOc-3TbE4W7PH4f7z6JNwOznFtH-JPIglXn769P5Kl4ORJ0e&_nc_ht=scontent.fsgn5-2.fna&oh=4a4dbfc66aa3a1fb5aeeefa754ce422f&oe=5E2E96E2","Nguyễn Văn Lương",20,300,null);
        Chef chef2 = new Chef("https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-1/p320x320/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlqW0sH4rpBiS2IPFfCEaorz-_7CDmDvZCV3YdL5u0-dyJcqxhMRJMtySaDEMPjS-uGvtyeUqP4ZJT4328aZLhU&_nc_ht=scontent.fsgn5-3.fna&oh=ea35b802e27df0b8c9f7bc852baec68f&oe=5E023271","Nguyễn Hoàng Bá Khánh",100,450,null);
        items.add(chef1);
        items.add(chef3);
        items.add(chef2);
        updateRcv();

        mTxtEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mView.getContext(), EditAccountActivity.class);
                startActivity(intent);
            }
        });

        mBtnLogout.setOnClickListener(this);
    }

    private void updateRcv() {
        if (mFollowAdapter == null) {
            mFollowAdapter = new FollowChefApdater(getContext(), items);
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
    public void onFail(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }
}
