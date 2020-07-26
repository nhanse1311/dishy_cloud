package com.example.dishycloud.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.MaterialAdapter;
import com.example.dishycloud.bottomSheets.BottomSheetChooseOption;
import com.example.dishycloud.bottomSheets.CallBackOption;
import com.example.dishycloud.models.ChooseOptionBottomSheet;
import com.example.dishycloud.models.Dishy;
import com.example.dishycloud.models.Material;
import com.example.dishycloud.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class EditTwoFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private ImageView mImgCheckEdit, mImgAddNewMaterial;
    private EditText mEdtMaterialName, mEdtQuality;
    private TextView mTxtUnit, txtUnitDialog;
    private LinearLayout mLLUnit;
    private RecyclerView mRecyclerView;
    private List<ChooseOptionBottomSheet> optionBottomSheets;
    private Button mBtnNexStep;
    private String mUnit;
    private MaterialAdapter mMaterialAdapter;
    private List<Material> mMaterials = new ArrayList<>();
    public static OnStepTwoEditClickListener mOnStepTwoEditClickListener;


    public static EditTwoFragment newInstance() {
        EditTwoFragment fragment = new EditTwoFragment();
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
        mView = inflater.inflate(R.layout.fragment_edit_two, container, false);
        initView();
        initData();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUIRV(mMaterials);
    }

    private void initView() {
        mImgCheckEdit = mView.findViewById(R.id.img_edit_edit);
        mEdtMaterialName = mView.findViewById(R.id.edt_name_nl_edit);
        mLLUnit = mView.findViewById(R.id.ll_nguyen_lieu_1_edit);
        mImgAddNewMaterial = mView.findViewById(R.id.img_button_add_edit);
        mTxtUnit = mView.findViewById(R.id.txt_unit_edit);
        mEdtQuality = mView.findViewById(R.id.edt_quality_material_edit);
        mBtnNexStep = mView.findViewById(R.id.btn_next_step_wto_edit);
        mRecyclerView = mView.findViewById(R.id.rcv_item_material_edit);
        mRecyclerView.setVisibility(View.VISIBLE);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
    }

    private void initData() {
        mImgCheckEdit.setOnClickListener(this);
        mLLUnit.setOnClickListener(this);
        mBtnNexStep.setOnClickListener(this);
        mImgAddNewMaterial.setOnClickListener(this);
        Intent intent = getActivity().getIntent();
        Recipe dishy = (Recipe) intent.getSerializableExtra("EDIT");
        mMaterials = new ArrayList<>();
        for (int i = 0; i < dishy.getMaterials().size(); i++) {
            if (i>0){
                mMaterials.add(dishy.getMaterials().get(i));
            }
        }
        mEdtMaterialName.setText(dishy.getMaterials().get(0).getName());
        mEdtMaterialName.setEnabled(false);
        mEdtQuality.setText(String.valueOf(dishy.getMaterials().get(0).getQuality()));
        mEdtQuality.setEnabled(false);
        mTxtUnit.setText(dishy.getMaterials().get(0).getUnit());
        mTxtUnit.setEnabled(false);
        updateUIRV(mMaterials);

        //Set values for bottom sheet unit
        optionBottomSheets = new ArrayList<>();
        optionBottomSheets.add(new ChooseOptionBottomSheet(1, "Gram"));
        optionBottomSheets.add(new ChooseOptionBottomSheet(2, "Kilogram"));
        optionBottomSheets.add(new ChooseOptionBottomSheet(3, "Millilit"));
        optionBottomSheets.add(new ChooseOptionBottomSheet(4, "Lit"));
        optionBottomSheets.add(new ChooseOptionBottomSheet(5, "Muỗng"));
        optionBottomSheets.add(new ChooseOptionBottomSheet(6, "Quả"));
        optionBottomSheets.add(new ChooseOptionBottomSheet(7, "Củ"));
        optionBottomSheets.add(new ChooseOptionBottomSheet(8, "Con"));
        optionBottomSheets.add(new ChooseOptionBottomSheet(9, "Gói"));
        optionBottomSheets.add(new ChooseOptionBottomSheet(10, "Chén"));
        optionBottomSheets.add(new ChooseOptionBottomSheet(11, "Tô"));
        optionBottomSheets.add(new ChooseOptionBottomSheet(12, "Không có"));
    }

    private void updateUIRV(List<Material> materials) {
        if (mMaterialAdapter == null) {
            mMaterialAdapter = new MaterialAdapter(getContext(), materials);
            mRecyclerView.setAdapter(mMaterialAdapter);
            mMaterialAdapter.setmOnItemMaterialClickListener(new MaterialAdapter.OnItemMaterialClickListener() {
                @Override
                public void onClick(Material material, int position) {
                    showDialogToEditMaterilRecycleView(material, position);
                }
            });
        } else {
            mMaterialAdapter.notifyDataSetChanged();
        }
    }

    private void bottomSheetUnit() {
        BottomSheetChooseOption bottomSheetChooseOption = new BottomSheetChooseOption(getContext(), "Đơn vị", optionBottomSheets);
        bottomSheetChooseOption.show(getFragmentManager(), "unitMaterial");
        bottomSheetChooseOption.getChooseString(new CallBackOption() {
            @Override
            public void chooseOption(ChooseOptionBottomSheet method, int position) {
                mTxtUnit.setText(method.getName());
            }
        });
    }

    private void bottomSheetUnitDialog() {
        BottomSheetChooseOption bottomSheetChooseOption = new BottomSheetChooseOption(getContext(), "Đơn vị", optionBottomSheets);
        bottomSheetChooseOption.show(getFragmentManager(), "unitMaterial");
        bottomSheetChooseOption.getChooseString(new CallBackOption() {
            @Override
            public void chooseOption(ChooseOptionBottomSheet method, int position) {
                mUnit = method.getName();
                txtUnitDialog.setText(mUnit);
            }
        });
    }

    private void dialogAddMaterial() {
        final Dialog dialog = new Dialog(getContext(), R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_add_material);
        Button btnOk = dialog.findViewById(R.id.btn_ok_add_material);
        final EditText edtName = dialog.findViewById(R.id.edt_name_material_dialog);
        final EditText edtQuality = dialog.findViewById(R.id.edt_quality_dialog);
        LinearLayout llUnit = dialog.findViewById(R.id.ll_dialog_unit_item);
        txtUnitDialog = dialog.findViewById(R.id.txt_dialog_unit);
        llUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetUnitDialog();
            }
        });
        dialog.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String quality = edtQuality.getText().toString().equals("") ? "0" : mEdtQuality.getText().toString();
                String unit = (txtUnitDialog.getText().toString() != null || !txtUnitDialog.getText().toString().equals("")) ? txtUnitDialog.getText().toString() : "";
                mMaterials.add(new Material(name, Double.parseDouble(quality), unit));
                updateUIRV(mMaterials);
                dialog.dismiss();
            }
        });
    }

    private void showDialogToEditMaterilOne(Material material) {
        final Dialog dialog = new Dialog(getContext(), R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_add_material);
        Button btnOk = dialog.findViewById(R.id.btn_ok_add_material);
        final EditText edtName = dialog.findViewById(R.id.edt_name_material_dialog);
        final EditText edtQuality = dialog.findViewById(R.id.edt_quality_dialog);
        LinearLayout llUnit = dialog.findViewById(R.id.ll_dialog_unit_item);
        txtUnitDialog = dialog.findViewById(R.id.txt_dialog_unit);
        edtName.setText(material.getName());
        edtQuality.setText(String.valueOf(material.getQuality()));
        txtUnitDialog.setText(material.getUnit());

        llUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetUnitDialog();
            }
        });
        dialog.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String quality = edtQuality.getText().toString();
                String unit = (txtUnitDialog.getText().toString() == null || txtUnitDialog.getText().toString().equals("")) ? "" : txtUnitDialog.getText().toString();
                mEdtMaterialName.setText(name);
                mEdtQuality.setText(quality);
                mTxtUnit.setText(unit);
                mEdtQuality.setEnabled(false);
                mEdtMaterialName.setEnabled(false);
                dialog.dismiss();
            }
        });
    }

    private void showDialogToEditMaterilRecycleView(Material material, final int position) {
        final Dialog dialog = new Dialog(getContext(), R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_add_material);
        Button btnOk = dialog.findViewById(R.id.btn_ok_add_material);
        final EditText edtName = dialog.findViewById(R.id.edt_name_material_dialog);
        final EditText edtQuality = dialog.findViewById(R.id.edt_quality_dialog);
        LinearLayout llUnit = dialog.findViewById(R.id.ll_dialog_unit_item);
        txtUnitDialog = dialog.findViewById(R.id.txt_dialog_unit);
        edtName.setText(material.getName());
        edtQuality.setText(String.valueOf(material.getQuality()));
        txtUnitDialog.setText(material.getUnit());

        llUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetUnitDialog();
            }
        });
        dialog.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String qualityStr = edtQuality.getText().toString().trim();
                Double quality = Double.parseDouble(qualityStr.equals("") ? "0" : qualityStr);
                String unit = (txtUnitDialog.getText().toString() == null || txtUnitDialog.getText().toString().equals("")) ? "" : txtUnitDialog.getText().toString();
                mMaterials.get(position).setName(name);
                mMaterials.get(position).setQuality(quality);
                mMaterials.get(position).setUnit(unit);
                updateUIRV(mMaterials);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_edit_edit:
                String name = mEdtMaterialName.getText().toString();
                String qualityStr = mEdtQuality.getText().toString().trim();
                Double quality = Double.parseDouble(qualityStr.equals("") ? "0" : qualityStr);
                String unit = mTxtUnit.getText().toString();
                Material material = new Material(name, quality, unit);
                showDialogToEditMaterilOne(material);
                break;
            case R.id.ll_nguyen_lieu_1_edit:
                bottomSheetUnit();
                break;
            case R.id.img_button_add_edit:
                mEdtMaterialName.setEnabled(false);
                mEdtQuality.setEnabled(false);
                mLLUnit.setEnabled(false);
                dialogAddMaterial();
                break;
            case R.id.btn_next_step_wto_edit:
                mOnStepTwoEditClickListener.onClick(2);
                break;
        }
    }

    public interface OnStepTwoEditClickListener {
        void onClick(int pager);
    }

}
