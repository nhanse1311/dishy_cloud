package com.example.dishycloud.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.StepMakeAdapter;
import com.example.dishycloud.models.Dishy;
import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.StepMake;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EditThreeFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private static int TAKE_PIC_1 = 9801;
    private static int TAKE_PIC_2 = 9802;
    private static int TAKE_PIC_1_DIALOG = 9811;
    private static int TAKE_PIC_2_DIALOG = 9812;

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
    private Uri mUriImage1, mUriImage2;
    private String mEncodeImg1 = "", mEncodeImg2 = "";

    public static EditThreeFragment newInstance() {
        EditThreeFragment fragment = new EditThreeFragment();
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
        mView = inflater.inflate(R.layout.fragment_edit_three, container, false);
        initView();
        initData();
        return mView;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUIRCV(mStepMakes);
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
        mImgPicOne = mView.findViewById(R.id.img_step_make_1_edit);
        mImgPicWto = mView.findViewById(R.id.img_step_make_2_edit);
        mImgAddMakeStep = mView.findViewById(R.id.img_button_add_make_step_edit);
        mImgEditStep1 = mView.findViewById(R.id.img_edit_make_step_edit);
        mEdtDescripStep = mView.findViewById(R.id.edt_descrip_make_step_edit);
        mCbCheckPrepairStage = mView.findViewById(R.id.cb_prepair_stage_edit);
        mBtn_done = mView.findViewById(R.id.btn_done_edit);
        mRcvStepMake = mView.findViewById(R.id.rcv_item_step_make_edit);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRcvStepMake.setLayoutManager(manager);
    }

    private void initData() {
        mImgPicOne.setOnClickListener(this);
        mImgPicWto.setOnClickListener(this);
        mImgAddMakeStep.setOnClickListener(this);
        mImgEditStep1.setOnClickListener(this);
        mBtn_done.setOnClickListener(this);
        mRcvStepMake.setVisibility(View.VISIBLE);

        Intent intent = getActivity().getIntent();
        Recipe dishy = (Recipe) intent.getSerializableExtra("EDIT");
        mStepMakes = new ArrayList<>();
        for (int i = 0; i < dishy.getSteps().size(); i++) {
            if (i>0){
                mStepMakes.add(dishy.getSteps().get(i));
            }
        }

        mEdtDescripStep.setText(dishy.getSteps().get(0).getDescription());
        mEdtDescripStep.setEnabled(false);
        mImgPicOne.setEnabled(false);
        mImgPicWto.setEnabled(false);
        Picasso.Builder builder = new Picasso.Builder(getContext());
        builder.build().load(dishy.getSteps().get(0).getImage1()).into(mImgPicOne);
        builder.build().load(dishy.getSteps().get(0).getImage2()).into(mImgPicWto);
        mCbCheckPrepairStage.setChecked(dishy.getSteps().get(0).isRepair());
        mCbCheckPrepairStage.setEnabled(false);
        updateUIRCV(dishy.getSteps());
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
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), TAKE_PIC_1);
        } else if (picNumber == 2) {
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), TAKE_PIC_2);
        } else if (picNumber == 3) {
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), TAKE_PIC_1_DIALOG);
        } else if (picNumber == 4) {
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), TAKE_PIC_2_DIALOG);
        }
    }

    private void updateUIRCV(List<StepMake> stepMakes) {
        if (mStepMakeAdapter == null) {
            mStepMakeAdapter = new StepMakeAdapter(getContext(), stepMakes);
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
                mStepMakes.add(new StepMake(desc, check, mUriImage1, mUriImage2));
                dialog.dismiss();
                updateUIRCV(mStepMakes);
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
                } else {
                    mStepMake.setUrlImgeOne(mUriImage1);
                }
                if (mBmImgDialog2 != null) {
                    mStepMake.setUrlImgWto(mUriImage2);
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
                updateUIRCV(mStepMakes);
                dialog.dismiss();
            }
        });
    }

    private void updateStepMakeOne(StepMake mStepMake) {
        mEdtDescripStep.setText(mStepMake.getDescription());
        mCbCheckPrepairStage.setChecked(mStepMake.isRepair());
        mImgPicOne.setImageURI(mStepMake.getUrlImgeOne());
        mImgPicWto.setImageURI(mStepMake.getUrlImgWto());
        if (mStepMake.getImage1() == null) {
            mImgPicOne.setImageResource(R.drawable.ic_add_picture);
        }
        if (mStepMake.getImage2() == null) {
            mImgPicWto.setImageResource(R.drawable.ic_add_picture);
        }
    }

    private void showDialogSuccess(){
        Dialog dialog = new Dialog(getContext(),R.style.Theme_Dialog);
        dialog.setContentView(R.layout.item_dialog_save);
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
            case R.id.img_step_make_1_edit:
                pickFromGallery(1);
                break;
            case R.id.img_step_make_2_edit:
                pickFromGallery(2);
                break;
            case R.id.img_button_add_make_step_edit:
                mEdtDescripStep.setEnabled(false);
                mCbCheckPrepairStage.setEnabled(false);
                mImgPicOne.setEnabled(false);
                mImgPicWto.setEnabled(false);
                mBmImgDialog1 = null;
                mBmImgDialog2 = null;
                showDialogToAddMakeStep();
                break;
            case R.id.img_edit_make_step_edit:
                String desc = mEdtDescripStep.getText().toString().trim();
                mCheckPrepairStage = mCbCheckPrepairStage.isChecked();
                mStepMake = new StepMake(desc, mCheckPrepairStage, mUriImage1,mUriImage2);
                showDialogToEditMakeStepOne(mStepMake);
                break;
            case R.id.btn_done_edit:
                showDialogSuccess();
                break;
        }
    }

}
