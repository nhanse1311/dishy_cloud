package com.example.dishycloud.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.utils.BaseUtils;
import com.example.dishycloud.views.CreateRecipeView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CreateRecipeStepThreeFragment extends Fragment implements View.OnClickListener, CreateRecipeView {
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
    private String mEncodeImg1 = "", mEncodeImg2 = "";
    private Uri mUriImage1, mUriImage2;
    private StepMake mStepMake;
    private List<StepMake> mStepMakes = new ArrayList<>();
    private StepMakeAdapter mStepMakeAdapter;
    private RecyclerView mRcvStepMake;
    private Dialog mDialogLoading;

    private CreateRecipePresenter mCreateRecipePresenter;
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
            mUriImage1 = data.getData();
            mImgDialog1.setImageURI(mUriImage1);
            ContentResolver cr = getActivity().getContentResolver();
            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
            mEncodeImg1 = System.currentTimeMillis() + "." + mimeTypeMap.getExtensionFromMimeType(cr.getType(mUriImage1));

        } else if (requestCode == TAKE_PIC_2_DIALOG && resultCode == Activity.RESULT_OK) {
            mUriImage2 = data.getData();
            mImgDialog2.setImageURI(mUriImage2);
            ContentResolver cr = getActivity().getContentResolver();
            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
            mEncodeImg2 = System.currentTimeMillis() + "." + mimeTypeMap.getExtensionFromMimeType(cr.getType(mUriImage2));
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

    private void pickFromGallery(int picNumber) {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (picNumber == 1) {
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), TAKE_PIC_1);
        } else if (picNumber == 2) {
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), TAKE_PIC_2);
        } else if (picNumber == 3) {
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), TAKE_PIC_1_DIALOG);
        } else if (picNumber == 4) {
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), TAKE_PIC_2_DIALOG);
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
                mStepMakes.add(new StepMake(desc, check, mEncodeImg1, mEncodeImg2, mUriImage1, mUriImage2));
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
                mCreateRecipePresenter.createRecipe(mDatabaseHelper.getToken());
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
}
