package com.example.dishycloud.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.FileUtils;
import android.provider.DocumentsContract;
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
import android.widget.Toast;

import com.example.dishycloud.R;
import com.example.dishycloud.bottomSheets.BottomSheetChooseOption;
import com.example.dishycloud.bottomSheets.CallBackOption;
import com.example.dishycloud.models.ChooseOptionBottomSheet;
import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.presenters.PostImagePresenter;
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.utils.BaseUtils;
import com.example.dishycloud.utils.ReadPathUtil;
import com.example.dishycloud.views.PostImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateRecipeStepOneFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, PostImageView {
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

    private String name, description, image, imagePath;
    private int timeCook, numberPeople;
    private int levelRecipe = 1;
    private String currentPhotoPath;

    private PostImagePresenter mPostImagePresenter;
    private DatabaseHelper mDatabaseHelper;
    private Dialog mDialogLoading;


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
            imagePath = ReadPathUtil.getPath(getContext(), data.getData());
            File file = new File(imagePath);
            image = "https://myappapi01.azurewebsites.net/images/"+file.getName();
            Uri uri = Uri.fromFile(new File(imagePath));
            mImgAvatar.setImageURI(uri);
            mImgAvatar.setScaleType(ImageView.ScaleType.FIT_XY);
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

        mDialogLoading = new Dialog(getContext(), R.style.Theme_Dialog);
        mDialogLoading.setContentView(R.layout.dialog_loading);
    }

    private void initData() {
        mImgAvatar.setOnClickListener(this);
        mBtnNextStep.setOnClickListener(this);
        mTxtLevelRecipe.setOnClickListener(this);

        mPostImagePresenter = new PostImagePresenter(getContext(), this);
        mDatabaseHelper = new DatabaseHelper(getContext());
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

    //Check permistion android 6.0
    private void checkPermistion() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
    }


    private void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        checkPermistion();
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        // Sets the type as image/*. This ensures only components of type image are selected
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

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
                BaseUtils.recipe = new Recipe(name, description, image,imagePath, timeCook, levelRecipe, numberPeople);
//                mPostImagePresenter.postImage(mDatabaseHelper.getToken(), BaseUtils.recipe.getImagePath());
//                mDialogLoading.show();
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

    @Override
    public void onPostImageSucces(List<String> imageName) {
        mDialogLoading.dismiss();
        Toast.makeText(getContext(),imageName.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPostImageFail(String fail) {
        mDialogLoading.dismiss();
//        Toast.makeText(getContext(), fail, Toast.LENGTH_LONG).show();
    }
}
