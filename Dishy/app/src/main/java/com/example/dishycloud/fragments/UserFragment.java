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

import com.example.dishycloud.R;
import com.example.dishycloud.activities.EditAccountActivity;
import com.example.dishycloud.adaptes.AlbumAdapter;
import com.example.dishycloud.adaptes.FollowChefApdater;
import com.example.dishycloud.adaptes.RecipeConcernAdapter;
import com.example.dishycloud.models.Chef;
import com.example.dishycloud.models.ItemsQuickSearch;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends Fragment {
    private View mView;
    private RecyclerView mRecycleView, mRecycleViewConcern;
    private FollowChefApdater mFollowAdapter;
    private AlbumAdapter albumAdapter;
    private RecipeConcernAdapter mRecipeConcernAdapter;
    private List<Chef> items;
    private List<ItemsQuickSearch> quickSearchList;
    private ImageView mTxtEditAccount;
    private Button mBtnLogout;

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
        mBtnLogout = mView.findViewById(R.id.btn_logout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManagerConcern = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mRecycleViewConcern.setLayoutManager(linearLayoutManagerConcern);
    }

    private void initData() {
        if (items == null) {
            items = new ArrayList<Chef>();
        }

        Chef chef1 = new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-1/p320x320/61090498_1285841494925963_1183091008456359936_n.jpg?_nc_cat=101&_nc_oc=AQkXDsimHahDSzxF7BS9NbBvgox8P-BAyPNh2DvJlOZdkZqhBm3KS206w5f7cw1PBneiFi6EtydeF5Gf1avxxUxS&_nc_ht=scontent.fsgn5-1.fna&oh=5092122e39724a586043169cf47d0696&oe=5E2FC72F","Nguyễn Thanh Nhàn",100,1000,null);
        Chef chef3 = new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/c0.0.320.320a/p320x320/10599638_1504520149789789_4487427289442049165_n.jpg?_nc_cat=107&_nc_oc=AQne8gyZOiKbnc5zvKNLPtE9JeuC3flPcvR_za52mOc-3TbE4W7PH4f7z6JNwOznFtH-JPIglXn769P5Kl4ORJ0e&_nc_ht=scontent.fsgn5-2.fna&oh=4a4dbfc66aa3a1fb5aeeefa754ce422f&oe=5E2E96E2","Nguyễn Văn Lương",20,300,null);
        Chef chef2 = new Chef("https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-1/p320x320/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlqW0sH4rpBiS2IPFfCEaorz-_7CDmDvZCV3YdL5u0-dyJcqxhMRJMtySaDEMPjS-uGvtyeUqP4ZJT4328aZLhU&_nc_ht=scontent.fsgn5-3.fna&oh=ea35b802e27df0b8c9f7bc852baec68f&oe=5E023271","Nguyễn Hoàng Bá Khánh",100,450,null);
        items.add(chef1);
        items.add(chef3);
        items.add(chef2);

        quickSearchList = new ArrayList<>();
        quickSearchList.add(new ItemsQuickSearch("Ăn sáng", "https://png.pngtree.com/png-vector/20190130/ourlarge/pngtree-food-elements-hand-drawn-cute-cartoon-breakfast-dessert-omelette-elementhand-drawn-png-image_613496.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Món chính", "https://png.pngtree.com/png-clipart/20190115/ourlarge/pngtree-winter-food-free-food-special-food-creative-food-png-image_359446.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Ăn vặt", "https://png.pngtree.com/png-clipart/20190117/ourlarge/pngtree-delicious-snacks-beautiful-snack-hand-painted-snacks-cartoon-snack-png-image_412624.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Bánh", "https://png.pngtree.com/png-vector/20190130/ourlarge/pngtree-food-elements-hand-drawn-cute-cartoon-gourmet-donuts-elementhand-drawn-foodcartoon-png-image_614532.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Ăn chay", "https://png.pngtree.com/png-vector/20190116/ourlarge/pngtree-vegetable-salad-food-vegetables-vegetable-salad-food-pattern-png-image_388734.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Healthy", "https://png.pngtree.com/png-vector/20190130/ourlarge/pngtree-mbe-style-cartoon-cute-vegetable-carrot-vegetablescute-vegetablesmbe-vegetablescarrot-png-image_590422.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Thức uống", "https://png.pngtree.com/png-clipart/20190115/ourlarge/pngtree-food-coffee-coffee-frappuccino-png-image_369400.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Snacks", "https://png.pngtree.com/png-clipart/20190117/ourlarge/pngtree-delicious-popcorn-nutritional-popcorn-snack-popcorn-hand-painted-popcorn-png-image_415111.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Lẩu", "https://png.pngtree.com/element_our/png_detail/20181127/404-lang-png_248262.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Salad", "https://png.pngtree.com/element_origin_min_pic/16/08/03/1457a194373fea7.jpg"));
        updateRcvConcern(quickSearchList);
        updateRcv();

        mTxtEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mView.getContext(), EditAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateRcv() {
        if (mFollowAdapter == null) {
            mFollowAdapter = new FollowChefApdater(getContext(), items);
            mRecycleView.setAdapter(mFollowAdapter);
        } else {
            mFollowAdapter.notifyDataSetChanged();
        }
    }

    private void updateRcvConcern(List<ItemsQuickSearch> quickSearchList){
        if (mRecipeConcernAdapter == null) {
            mRecipeConcernAdapter = new RecipeConcernAdapter(getContext(), quickSearchList);
            mRecycleViewConcern.setAdapter(mRecipeConcernAdapter);
        } else {
            mRecipeConcernAdapter.notifyDataSetChanged();
        }
    }

    public void clickToLogout(View view) {
        System.exit(0);
    }

}
