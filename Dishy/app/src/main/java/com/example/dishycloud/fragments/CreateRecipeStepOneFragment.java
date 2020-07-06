package com.example.dishycloud.fragments;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import com.example.dishycloud.R;
import com.example.dishycloud.bottomSheets.BottomSheetChooseOption;
import com.example.dishycloud.bottomSheets.CallBackOption;
import com.example.dishycloud.models.ChooseOptionBottomSheet;
import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.utils.BaseUtils;

import java.io.ByteArrayOutputStream;
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
    private EditText mEdtRecipeName;
    private EditText mEdtDecription;
    private EditText mEdtNumberPeople;
    private EditText mEdtTimeCook;

    private String name, description, image;
    private int timeCook, numberPeople;
    private int levelRecipe = 1;

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
            mImgAvatar.setImageURI(mUriAvatar);
            mImgAvatar.setScaleType(ImageView.ScaleType.FIT_XY);
            ContentResolver cr = getActivity().getContentResolver();
            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
            image = System.currentTimeMillis()+"."+mimeTypeMap.getExtensionFromMimeType(cr.getType(mUriAvatar));

        }
    }

    private void initView() {
        mImgAvatar = mView.findViewById(R.id.img_avatar_recipe);
        mBtnNextStep = mView.findViewById(R.id.btn_next_step);
        mTxtLevelRecipe = mView.findViewById(R.id.txt_level_repice);
        mEdtRecipeName = mView.findViewById(R.id.edt_name_recipe_cr_one);
        mEdtDecription = mView.findViewById(R.id.edt_description_cr_one);
        mEdtNumberPeople = mView.findViewById(R.id.edt_number_people_cr_one);
        mEdtTimeCook = mView.findViewById(R.id.edt_time_cooking_cr_one);
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
                levelRecipe = method.getId();
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
                name = mEdtRecipeName.getText().toString().trim();
                description = mEdtDecription.getText().toString().trim();
                timeCook = Integer.parseInt(mEdtTimeCook.getText().toString().trim());
                numberPeople = Integer.parseInt(mEdtNumberPeople.getText().toString().trim());
                BaseUtils.recipe = new Recipe(name, description, image, timeCook, levelRecipe, numberPeople);
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
