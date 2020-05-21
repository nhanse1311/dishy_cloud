package com.example.dishycloud.activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.FragmentHomeAdapter;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentHomeAdapter mFragmentHomeAdapter;
    private ViewPager mViewPager;
    private LinearLayout mLLHome, mLLHistory, mLLAlbum, mLLUser;
    private TextView mTxtHome, mTxtHistory, mTxtAlbum, mTxtUser;
    private ImageView mImgHome, mImgHistory, mImgAlbum, mImgUser;

    private ImageView mImgChef;
    private TextView mEdtSearch;

    private ImageView  mImgNotfy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2019){
            mViewPager.setCurrentItem(2);
        }
    }

    private void initView() {
        mViewPager = findViewById(R.id.vp_home);
        mLLHome = findViewById(R.id.ll_button_home);
        mLLHistory = findViewById(R.id.ll_button_history);
        mLLAlbum = findViewById(R.id.ll_button_album);
        mLLUser = findViewById(R.id.ll_button_user);
        mTxtHome = findViewById(R.id.txt_title_home);
        mTxtHistory = findViewById(R.id.txt_title_history);
        mTxtAlbum = findViewById(R.id.txt_title_album);
        mTxtUser = findViewById(R.id.txt_title_user);
        mImgHome = findViewById(R.id.img_icon_home);
        mImgHistory = findViewById(R.id.img_icon_history);
        mImgAlbum = findViewById(R.id.img_icon_album);
        mImgUser = findViewById(R.id.img_icon_user);
        mImgChef = findViewById(R.id.img_chef);

        mEdtSearch = findViewById(R.id.edt_search_home);

        mImgNotfy = findViewById(R.id.ic_notification);
    }

    private void initData() {
        mLLHome.setOnClickListener(this);
        mLLHistory.setOnClickListener(this);
        mLLAlbum.setOnClickListener(this);
        mLLUser.setOnClickListener(this);
        mImgChef.setOnClickListener(this);

        mEdtSearch.setOnClickListener(this);


        mImgNotfy.setOnClickListener(this);

        mFragmentHomeAdapter = new FragmentHomeAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentHomeAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    setPageHome();
                } else if (position == 1) {
                    setPageHistory();
                } else if (position == 2) {
                    setPageAlbum();
                } else if (position == 3) {
                    setPageUser();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setPageHome() {
        mImgHome.setImageResource(R.drawable.ic_home);
        mTxtHome.setTextColor(Color.parseColor("#f0f0f0f0"));
        mImgHistory.setImageResource(R.drawable.ic_history_no_select);
        mTxtHistory.setTextColor(Color.parseColor("#BEBBBB"));
        mImgAlbum.setImageResource(R.drawable.ic_album_no_select);
        mTxtAlbum.setTextColor(Color.parseColor("#BEBBBB"));
        mImgUser.setImageResource(R.drawable.ic_person_so_select);
        mTxtUser.setTextColor(Color.parseColor("#BEBBBB"));
    }

    private void setPageHistory() {
        mImgHome.setImageResource(R.drawable.ic_home_no_select);
        mTxtHome.setTextColor(Color.parseColor("#BEBBBB"));
        mImgHistory.setImageResource(R.drawable.ic_history);
        mTxtHistory.setTextColor(Color.parseColor("#f0f0f0f0"));
        mImgAlbum.setImageResource(R.drawable.ic_album_no_select);
        mTxtAlbum.setTextColor(Color.parseColor("#BEBBBB"));
        mImgUser.setImageResource(R.drawable.ic_person_so_select);
        mTxtUser.setTextColor(Color.parseColor("#BEBBBB"));
    }

    private void setPageAlbum() {
        mImgHome.setImageResource(R.drawable.ic_home_no_select);
        mTxtHome.setTextColor(Color.parseColor("#BEBBBB"));
        mImgHistory.setImageResource(R.drawable.ic_history_no_select);
        mTxtHistory.setTextColor(Color.parseColor("#BEBBBB"));
        mImgAlbum.setImageResource(R.drawable.ic_album);
        mTxtAlbum.setTextColor(Color.parseColor("#f0f0f0f0"));
        mImgUser.setImageResource(R.drawable.ic_person_so_select);
        mTxtUser.setTextColor(Color.parseColor("#BEBBBB"));
    }

    private void setPageUser() {
        mImgHome.setImageResource(R.drawable.ic_home_no_select);
        mTxtHome.setTextColor(Color.parseColor("#BEBBBB"));
        mImgHistory.setImageResource(R.drawable.ic_history_no_select);
        mTxtHistory.setTextColor(Color.parseColor("#BEBBBB"));
        mImgAlbum.setImageResource(R.drawable.ic_album_no_select);
        mTxtAlbum.setTextColor(Color.parseColor("#BEBBBB"));
        mImgUser.setImageResource(R.drawable.ic_person_home);
        mTxtUser.setTextColor(Color.parseColor("#f0f0f0f0"));
    }

    private void onMoveToCreateRecipeActivity() {
        Intent intent = new Intent(HomeActivity.this, CreateRecipeActivity.class);
        HomeActivity.this.startActivity(intent);
    }
    private void onMoveToNotificationActivity() {
        Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
        HomeActivity.this.startActivity(intent);
    }

    private void onMoveToSearchActivity() {
        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
        HomeActivity.this.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_button_home:
                setPageHome();
                mViewPager.setCurrentItem(0);
                break;
            case R.id.ll_button_history:
                setPageHistory();
                mViewPager.setCurrentItem(1);
                break;
            case R.id.ll_button_album:
                mViewPager.setCurrentItem(2);
                setPageAlbum();
                break;
            case R.id.ll_button_user:
                mViewPager.setCurrentItem(3);
                setPageUser();
                break;
            case R.id.img_chef:
                onMoveToCreateRecipeActivity();
                break;

            case R.id.edt_search_home:
                onMoveToSearchActivity();
                break;

            case R.id.ic_notification:
                onMoveToNotificationActivity();
                break;
        }
    }
}
