package com.example.dishycloud.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.FragmentAlbumAdapter;
import com.example.dishycloud.bottomSheets.BottomSheetChooseOption;
import com.example.dishycloud.bottomSheets.CallBackOption;
import com.example.dishycloud.models.ChooseOptionBottomSheet;

import java.util.ArrayList;
import java.util.List;

public class AlbumFragment extends Fragment {
    private View mView;
    private TextView txt_saved_recipe, txt_my_recipe;
    private ViewPager pager_album;
    private ImageView mImgFillterCaAlbum;
    private LinearLayout ll_saved_recipe, ll_my_recipe;
    private LinearLayout ll_tab_saved_recipe, ll_tab_my_recipe;

    private FragmentAlbumAdapter fragmentAlbumAdapter;

    public static AlbumFragment newInstance() {
        AlbumFragment fragment = new AlbumFragment();
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
        mView = inflater.inflate(R.layout.fragment_album, container, false);
        initView();
        initData();
        return mView;
    }

    private void initView() {
        txt_saved_recipe = mView.findViewById(R.id.txt_saved_recipe);
        txt_my_recipe = mView.findViewById(R.id.txt_my_recipe);
        pager_album = mView.findViewById(R.id.pager_album);
        ll_saved_recipe = mView.findViewById(R.id.ll_saved_recipe);
        ll_my_recipe = mView.findViewById(R.id.ll_my_recipe);
        ll_tab_my_recipe = mView.findViewById(R.id.ll_tab_my_recipe);
        ll_tab_saved_recipe = mView.findViewById(R.id.ll_tab_saved_recipe);
        mImgFillterCaAlbum = mView.findViewById(R.id.img_fillter_ca_album);
    }

    private void initData() {
        mImgFillterCaAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetFillter();
            }
        });

        fragmentAlbumAdapter = new FragmentAlbumAdapter(getChildFragmentManager());
        pager_album.setAdapter(fragmentAlbumAdapter);
        pager_album.setCurrentItem(0);
        pager_album.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    setPageSavedRecipe();
                } else if (position == 1) {
                    setPageMyRecipe();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ll_tab_saved_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager_album.setCurrentItem(0);
            }
        });

        ll_tab_my_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager_album.setCurrentItem(1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2019){
            pager_album.setCurrentItem(1);
        }
    }

    private void setPageSavedRecipe() {
        ll_saved_recipe.setBackgroundResource(R.color.colorMain);
        ll_my_recipe.setBackgroundResource(R.color.colorWhite);
    }

    private void setPageMyRecipe() {
        ll_saved_recipe.setBackgroundResource(R.color.colorWhite);
        ll_my_recipe.setBackgroundResource(R.color.colorMain);
    }

    private void showBottomSheetFillter() {
        List<ChooseOptionBottomSheet> listSort = new ArrayList<>();
        listSort.add(new ChooseOptionBottomSheet(1, "Theo top tăng dần"));
        listSort.add(new ChooseOptionBottomSheet(2, "Theo top giảm dần"));
        listSort.add(new ChooseOptionBottomSheet(3, "Theo lượt thích nhiều nhất"));
        listSort.add(new ChooseOptionBottomSheet(4, "Theo lượt thích ít nhất"));
        BottomSheetChooseOption bottomSheetChooseOption = new BottomSheetChooseOption(mView.getContext(), "Sắp xếp", listSort);
        bottomSheetChooseOption.show(getFragmentManager(), "sortOption");
        bottomSheetChooseOption.getChooseString(new CallBackOption() {
            @Override
            public void chooseOption(ChooseOptionBottomSheet method, int position) {

            }
        });
    }
}
