package com.example.dishycloud.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.StepMakeAdapter;
import com.example.dishycloud.models.StepMake;
import com.example.dishycloud.presenters.CreateRecipePresenter;
import com.example.dishycloud.presenters.GetUserPresenter;
import com.example.dishycloud.presenters.PostImagePresenter;
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.utils.BaseUtils;
import com.example.dishycloud.utils.ReadPathUtil;
import com.example.dishycloud.views.CreateRecipeView;
import com.example.dishycloud.views.PostImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CreateRecipeStepThreeFragment extends Fragment implements View.OnClickListener, CreateRecipeView, PostImageView {
    private static int TAKE_PIC_1 = 9801;
    private static int TAKE_PIC_2 = 9802;
    private static int TAKE_PIC_1_DIALOG = 9811;
    private static int TAKE_PIC_2_DIALOG = 9812;

    private View mView;
    private EditText mEdtDescripStep;
    private Button mBtn_done;
    private CheckBox mCbCheckPrepairStage;
    private Boolean mCheckPrepairStage;
    private ImageView mImgDialog1, mImgDialog2;
    private ImageView mImgPicOne, mImgPicWto, mImgAddMakeStep, mImgEditStep1;
    private Bitmap mBmImgDialog1, mBmImgDialog2;
    private String mEncodeImg1 = "", mEncodeImg2 = "", mImg1Path, mImg2Path;
    private Uri mUriImage1, mUriImage2;
    private StepMake mStepMake;
    private List<StepMake> mStepMakes = new ArrayList<>();
    private StepMakeAdapter mStepMakeAdapter;
    private RecyclerView mRcvStepMake;
    private Dialog mDialogLoading;

    private CreateRecipePresenter mCreateRecipePresenter;
    private PostImagePresenter mPostImagePresenter;
    private DatabaseHelper mDatabaseHelper;


    public static CreateRecipeStepThreeFragment newInstance() {
        CreateRecipeStepThreeFragment fragment = new CreateRecipeStepThreeFragment();
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
        mView = inflater.inflate(R.layout.fragment_create_recipe_step_three, container, false);
        initView();
        initData();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUIRCV();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PIC_1_DIALOG && resultCode == Activity.RESULT_OK) {
            mImg1Path = ReadPathUtil.getPath(getContext(), data.getData());

            mUriImage1 = Uri.fromFile(new File(mImg1Path));
            mImgDialog1.setImageURI(mUriImage1);
            mImgDialog1.setScaleType(ImageView.ScaleType.FIT_XY);

        } else if (requestCode == TAKE_PIC_2_DIALOG && resultCode == Activity.RESULT_OK) {
            mImg2Path = ReadPathUtil.getPath(getContext(), data.getData());

            mUriImage2 = Uri.fromFile(new File(mImg2Path));
            mImgDialog2.setImageURI(mUriImage2);
            mImgDialog2.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    private void initView() {
        mImgPicOne = mView.findViewById(R.id.img_step_make_1);
        mImgPicWto = mView.findViewById(R.id.img_step_make_2);
        mImgAddMakeStep = mView.findViewById(R.id.img_button_add_make_step);
        mImgEditStep1 = mView.findViewById(R.id.img_edit_make_step);
        mEdtDescripStep = mView.findViewById(R.id.edt_descrip_make_step);
        mCbCheckPrepairStage = mView.findViewById(R.id.cb_prepair_stage);
        mBtn_done = mView.findViewById(R.id.btn_done);
        mRcvStepMake = mView.findViewById(R.id.rcv_item_step_make);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRcvStepMake.setLayoutManager(manager);

        mDialogLoading = new Dialog(getContext(), R.style.Theme_Dialog);
        mDialogLoading.setContentView(R.layout.dialog_loading);
    }

    private void initData() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        mCreateRecipePresenter = new CreateRecipePresenter(this);
        mPostImagePresenter = new PostImagePresenter(getContext(), this);

        mImgPicOne.setOnClickListener(this);
        mImgPicWto.setOnClickListener(this);
        mImgAddMakeStep.setOnClickListener(this);
        mImgEditStep1.setOnClickListener(this);
        mBtn_done.setOnClickListener(this);
        if (mStepMakes != null) {
            mRcvStepMake.setVisibility(View.VISIBLE);
        }
        updateUIRCV();
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

    private void pickFromGallery(int picNumber) {
        checkPermistion();
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (picNumber == 1) {
            startActivityForResult(intent, TAKE_PIC_1);
        } else if (picNumber == 2) {
            startActivityForResult(intent, TAKE_PIC_2);
        } else if (picNumber == 3) {
            startActivityForResult(intent, TAKE_PIC_1_DIALOG);
        } else if (picNumber == 4) {
            startActivityForResult(intent, TAKE_PIC_2_DIALOG);
        }
    }

    private void updateUIRCV() {
        if (mStepMakeAdapter == null) {
            mStepMakeAdapter = new StepMakeAdapter(getContext(), mStepMakes);
            mRcvStepMake.setAdapter(mStepMakeAdapter);
            mStepMakeAdapter.setmOnStepMakeClickListener(new StepMakeAdapter.OnStepMakeClickListener() {
                @Override
                public void onClick(StepMake stepMake, int position) {
                    showDialogToEditMakeStepInRecylerView(stepMake, position);
                }
            });
        } else {
            mStepMakeAdapter.notifyDataSetChanged();
        }
    }

    private void showDialogToAddMakeStep() {
        final Dialog dialog = new Dialog(getContext(), R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_add_make_step);
        dialog.show();
        final EditText edtDes = dialog.findViewById(R.id.edt_descrip_make_step_dialog);
        final CheckBox cbPrepair = dialog.findViewById(R.id.cb_prepair_stage_dialog);
        mImgDialog1 = dialog.findViewById(R.id.img_step_make_1_dialog);
        mImgDialog2 = dialog.findViewById(R.id.img_step_make_2_dialog);

        mImgDialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery(3);
            }
        });
        mImgDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery(4);
            }
        });

        Button btnOK = dialog.findViewById(R.id.btn_ok_add_make_step_dialog);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc = edtDes.getText().toString().trim();
                boolean check = cbPrepair.isChecked();
                mStepMakes.add(new StepMake(desc, check, mEncodeImg1, mEncodeImg2, mUriImage1, mUriImage2, mImg1Path, mImg2Path));
                dialog.dismiss();
                updateUIRCV();
            }
        });
    }

    private void showDialogToEditMakeStepOne(StepMake stepMake) {
        final Dialog dialog = new Dialog(getContext(), R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_add_make_step);
        dialog.show();
        Button btnOK = dialog.findViewById(R.id.btn_ok_add_make_step_dialog);
        final EditText edtDes = dialog.findViewById(R.id.edt_descrip_make_step_dialog);
        final CheckBox cbPrepair = dialog.findViewById(R.id.cb_prepair_stage_dialog);
        mImgDialog1 = dialog.findViewById(R.id.img_step_make_1_dialog);
        mImgDialog2 = dialog.findViewById(R.id.img_step_make_2_dialog);

        edtDes.setText(stepMake.getDescription());
        cbPrepair.setChecked(stepMake.isRepair());
        mImgDialog1.setImageURI(stepMake.getUrlImgeOne());
        mImgDialog2.setImageURI(stepMake.getUrlImgWto());

        mImgDialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery(3);
            }
        });
        mImgDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery(4);
            }
        });
        //Button Ok
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc = edtDes.getText().toString().trim();
                boolean check = cbPrepair.isChecked();
                mStepMake.setDescription(desc);
                mStepMake.setRepair(check);
                if (mBmImgDialog1 != null) {
                    mStepMake.setUrlImgeOne(mUriImage1);
                    mStepMake.setImage1(mEncodeImg1);
                } else {
                    mStepMake.setUrlImgeOne(mUriImage1);
                }
                if (mBmImgDialog2 != null) {
                    mStepMake.setUrlImgWto(mUriImage2);
                    mStepMake.setImage2(mEncodeImg2);
                } else {
                    mStepMake.setUrlImgWto(mUriImage2);
                }
                updateStepMakeOne(mStepMake);
                dialog.dismiss();

            }
        });
    }

    private void showDialogToEditMakeStepInRecylerView(final StepMake stepMake, final int position) {
        final Dialog dialog = new Dialog(getContext(), R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_add_make_step);
        dialog.show();

        Button btnOK = dialog.findViewById(R.id.btn_ok_add_make_step_dialog);
        final EditText edtDes = dialog.findViewById(R.id.edt_descrip_make_step_dialog);
        final CheckBox cbPrepair = dialog.findViewById(R.id.cb_prepair_stage_dialog);
        mImgDialog1 = dialog.findViewById(R.id.img_step_make_1_dialog);
        mImgDialog2 = dialog.findViewById(R.id.img_step_make_2_dialog);

        edtDes.setText(stepMake.getDescription());
        cbPrepair.setChecked(stepMake.isRepair());
        mImgDialog1.setImageURI(stepMake.getUrlImgeOne());
        mImgDialog2.setImageURI(stepMake.getUrlImgWto());

        mImgDialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery(3);
            }
        });
        mImgDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery(4);
            }
        });
        //Button Ok
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc = edtDes.getText().toString().trim();
                boolean check = cbPrepair.isChecked();
                mStepMakes.get(position).setDescription(desc);
                mStepMakes.get(position).setRepair(check);
                mStepMakes.get(position).setUrlImgeOne(mUriImage1);
                mStepMakes.get(position).setUrlImgWto(mUriImage2);
                updateUIRCV();
                dialog.dismiss();
            }
        });
    }

    private void updateStepMakeOne(StepMake mStepMake) {
        mEdtDescripStep.setText(mStepMake.getDescription());
        mCbCheckPrepairStage.setChecked(mStepMake.isRepair());
        mImgPicOne.setImageURI(mStepMake.getUrlImgeOne());
        mImgPicWto.setImageURI(mStepMake.getUrlImgWto());
        if (mStepMake.getUrlImgeOne() == null) {
            mImgPicOne.setImageResource(R.drawable.ic_add_picture);
        }
        if (mStepMake.getUrlImgWto() == null) {
            mImgPicWto.setImageResource(R.drawable.ic_add_picture);
        }
    }

    private void showDialogSuccess(){
        Dialog dialog = new Dialog(getContext(),R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_success);
        dialog.show();
        Button btnOk = dialog.findViewById(R.id.btn_ok_success_dialog);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_step_make_1:
                pickFromGallery(1);
                break;
            case R.id.img_step_make_2:
                pickFromGallery(2);
                break;
            case R.id.img_button_add_make_step:
                mEdtDescripStep.setEnabled(false);
                mCbCheckPrepairStage.setEnabled(false);
                mImgPicOne.setEnabled(false);
                mImgPicWto.setEnabled(false);
                mBmImgDialog1 = null;
                mBmImgDialog2 = null;
                showDialogToAddMakeStep();
                break;
            case R.id.img_edit_make_step:
                String desc = mEdtDescripStep.getText().toString().trim();
                mCheckPrepairStage = mCbCheckPrepairStage.isChecked();
                mStepMake = new StepMake(desc, mCheckPrepairStage, mUriImage1, mUriImage2);
                showDialogToEditMakeStepOne(mStepMake);
                break;
            case R.id.btn_done:
                BaseUtils.recipe.setSteps(mStepMakes);
                String token = mDatabaseHelper.getToken();
                mDialogLoading.show();
                List<String> listImagePath = new ArrayList<>();
                listImagePath.add(BaseUtils.recipe.getImagePath());
                for (int i = 0; i < BaseUtils.recipe.getSteps().size(); i++) {
                    listImagePath.add(BaseUtils.recipe.getSteps().get(i).getImagePath1());
                    listImagePath.add(BaseUtils.recipe.getSteps().get(i).getImagePath2());
                }
                mPostImagePresenter.postImage(token, listImagePath);
                break;
        }
    }

    @Override
    public void onSuccess() {
        showDialogSuccess();
        mDialogLoading.dismiss();
    }

    @Override
    public void onFail(String message) {
        mDialogLoading.dismiss();
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPostImageSucces(List<String> imageName) {
        BaseUtils.recipe.setImage("https://myappapi01.azurewebsites.net/images/" + imageName.get(0));
        int sizeStep = BaseUtils.recipe.getSteps().size();
        int sizeImageName = imageName.size();
        int value1 = 1;
        int value2 = 2;
        for(int i =0; i< sizeStep;i++){
            BaseUtils.recipe.getSteps().get(i).setImage1("https://myappapi01.azurewebsites.net/images/" + imageName.get(value1));
            if(value2<sizeImageName){
                BaseUtils.recipe.getSteps().get(i).setImage2("https://myappapi01.azurewebsites.net/images/" + imageName.get(value2));
            }
            value1+=2;
            value2+=2;
        }
        mCreateRecipePresenter.createRecipe(mDatabaseHelper.getToken(), BaseUtils.recipe);
        System.out.println();
    }

    @Override
    public void onPostImageFail(String fail) {

    }
}
