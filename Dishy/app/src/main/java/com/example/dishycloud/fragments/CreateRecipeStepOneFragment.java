package com.example.dishycloud.fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import com.example.dishycloud.R;
import com.example.dishycloud.bottomSheets.BottomSheetChooseOption;
import com.example.dishycloud.bottomSheets.CallBackOption;
import com.example.dishycloud.models.ChooseOptionBottomSheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateRecipeStepOneFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static int PICK_IMAGE_REQUEST = 1311;
    private View mView;
    private ImageView mImgAvatar;
    private TextView mTxtLevelRecipe;
    private Uri mUriAvatar;
    private Toolbar mToolbar;
    private Button mBtnNextStep;
    private List<ChooseOptionBottomSheet> mLevelRecipes;
    public static OnNextStepListener mOnNextStepListener;


    //interface to click nextStep
    public interface OnNextStepListener {
        void onNextStepWto(int nextPage);
    }

    public static CreateRecipeStepOneFragment newInstance() {
        CreateRecipeStepOneFragment fragment = new CreateRecipeStepOneFragment();
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
        mView = inflater.inflate(R.layout.fragment_create_recipe_step_one, container, false);
        initView();
        initData();
        return mView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {

            mUriAvatar = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mUriAvatar);
                // Log.d(TAG, String.valueOf(bitmap));
                mImgAvatar.setImageBitmap(bitmap);
                mImgAvatar.setScaleType(ImageView.ScaleType.FIT_XY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initView() {
        mImgAvatar = mView.findViewById(R.id.img_avatar_recipe);
        mBtnNextStep = mView.findViewById(R.id.btn_next_step);
        mTxtLevelRecipe = mView.findViewById(R.id.txt_level_repice);
    }

    private void initData() {
        mImgAvatar.setOnClickListener(this);
        mBtnNextStep.setOnClickListener(this);
        mTxtLevelRecipe.setOnClickListener(this);
    }

    public void showBottomSheetLevelRepice() {
        mLevelRecipes = new ArrayList<>();
        mLevelRecipes.add(new ChooseOptionBottomSheet(1, "Dễ"));
        mLevelRecipes.add(new ChooseOptionBottomSheet(2, "Trung Bình"));
        mLevelRecipes.add(new ChooseOptionBottomSheet(3, "Khó"));
        BottomSheetChooseOption bottomSheetLevelRecipe = new BottomSheetChooseOption(getContext(), "Độ Khó", mLevelRecipes);
        bottomSheetLevelRecipe.show(getChildFragmentManager(), "levelRecipeRecord");
        bottomSheetLevelRecipe.getChooseString(new CallBackOption() {
            @Override
            public void chooseOption(ChooseOptionBottomSheet method, int position) {
                mTxtLevelRecipe.setText(method.getName());
            }
        });
    }


    private void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_avatar_recipe:
                pickFromGallery();
                break;
            case R.id.btn_next_step:
                mOnNextStepListener.onNextStepWto(1);
                break;
            case R.id.txt_level_repice:
                showBottomSheetLevelRepice();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mTxtLevelRecipe.setText(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        mTxtLevelRecipe.setText(adapterView.getFirstVisiblePosition());
    }
}
