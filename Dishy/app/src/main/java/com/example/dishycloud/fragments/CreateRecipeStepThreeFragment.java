package com.example.dishycloud.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.StepMakeAdapter;
import com.example.dishycloud.models.StepMake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CreateRecipeStepThreeFragment extends Fragment implements View.OnClickListener {
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
    private Bitmap mBmImg1, mBmImg2, mBmImgDialog1, mBmImgDialog2;
    private Uri mUriAvatar;
    private StepMake mStepMake;
    private List<StepMake> mStepMakes = new ArrayList<>();
    private StepMakeAdapter mStepMakeAdapter;
    private RecyclerView mRcvStepMake;

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
        if (requestCode == TAKE_PIC_1 && resultCode == Activity.RESULT_OK) {
            mUriAvatar = data.getData();
            try {
                mBmImgDialog1 = null;
                mBmImg1 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mUriAvatar);
                mImgPicOne.setImageBitmap(mBmImg1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == TAKE_PIC_2 && resultCode == Activity.RESULT_OK) {
            mUriAvatar = data.getData();
            try {
                mBmImgDialog2 = null;
                mBmImg2 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mUriAvatar);
                mImgPicWto.setImageBitmap(mBmImg2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == TAKE_PIC_1_DIALOG && resultCode == Activity.RESULT_OK) {
            mUriAvatar = data.getData();
            try {
                mBmImg1 = null;
                mBmImgDialog1 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mUriAvatar);
                mImgDialog1.setImageBitmap(mBmImgDialog1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == TAKE_PIC_2_DIALOG && resultCode == Activity.RESULT_OK) {
            mUriAvatar = data.getData();
            try {
                mBmImg2 = null;
                mBmImgDialog2 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mUriAvatar);
                mImgDialog2.setImageBitmap(mBmImgDialog2);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    }

    private void initData() {
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
                mStepMakes.add(new StepMake(desc, check, mBmImgDialog1, mBmImgDialog2));
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

        edtDes.setText(stepMake.getDescrip());
        cbPrepair.setChecked(stepMake.isPrepairStage());
        mImgDialog1.setImageBitmap(stepMake.getImageOne());
        mImgDialog2.setImageBitmap(stepMake.getImageTwo());

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
                mStepMake.setDescrip(desc);
                mStepMake.setPrepairStage(check);
                if (mBmImgDialog1 != null) {
                    mStepMake.setImageOne(mBmImgDialog1);
                } else {
                    mStepMake.setImageOne(mBmImg1);
                }
                if (mBmImgDialog2 != null) {
                    mStepMake.setImageTwo(mBmImgDialog2);
                } else {
                    mStepMake.setImageTwo(mBmImg2);
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

        edtDes.setText(stepMake.getDescrip());
        cbPrepair.setChecked(stepMake.isPrepairStage());
        mImgDialog1.setImageBitmap(stepMake.getImageOne());
        mImgDialog2.setImageBitmap(stepMake.getImageTwo());

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
                mStepMakes.get(position).setDescrip(desc);
                mStepMakes.get(position).setPrepairStage(check);
                mStepMakes.get(position).setImageOne(mBmImgDialog1);
                mStepMakes.get(position).setImageOne(mBmImgDialog2);
                updateUIRCV();
                dialog.dismiss();
            }
        });
    }

    private void updateStepMakeOne(StepMake mStepMake) {
        mEdtDescripStep.setText(mStepMake.getDescrip());
        mCbCheckPrepairStage.setChecked(mStepMake.isPrepairStage());
        mImgPicOne.setImageBitmap(mStepMake.getImageOne());
        mImgPicWto.setImageBitmap(mStepMake.getImageTwo());
        if (mStepMake.getImageOne() == null) {
            mImgPicOne.setImageResource(R.drawable.ic_add_picture);
        }
        if (mStepMake.getImageTwo() == null) {
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
                mStepMake = new StepMake(desc, mCheckPrepairStage, mBmImg1 != null ? mBmImg1 : mBmImgDialog1, mBmImg2 != null ? mBmImg2 : mBmImgDialog2);
                showDialogToEditMakeStepOne(mStepMake);
                break;
            case R.id.btn_done:
                showDialogSuccess();
                break;
        }
    }
}
