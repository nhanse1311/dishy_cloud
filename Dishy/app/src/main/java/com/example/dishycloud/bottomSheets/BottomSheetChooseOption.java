package com.example.dishycloud.bottomSheets;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.BoottomSheetLevelRecipeAdapter;
import com.example.dishycloud.models.ChooseOptionBottomSheet;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class BottomSheetChooseOption extends BottomSheetDialogFragment {
    private View mView;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private LinearLayout mLLDone;
    private TextView mTxtTile;
    private String title;
    private List<ChooseOptionBottomSheet> mOptions;

    public BottomSheetChooseOption(Context mContext, String title, List<ChooseOptionBottomSheet> mOptions) {
        this.mContext = mContext;
        this.title = title;
        this.mOptions = mOptions;
    }

    private BoottomSheetLevelRecipeAdapter mLevelRecipeAdapter;
    private CallBackOption mCallBackOption;
    private int mPositionChoose;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.bottom_sheet_level_recipe, container, false);
        initialView();
        return mView;
    }

    private void initialView() {
        mRecyclerView = mView.findViewById(R.id.rcv_bottom_sheet);
        mTxtTile = mView.findViewById(R.id.text_view_name_bottom_sheet);
        mLLDone = mView.findViewById(R.id.ll_buttom_done_bottom_sheet);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mTxtTile.setText(title);
        updateUI();
        mLLDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBackOption.chooseOption(mOptions.get(mPositionChoose), mPositionChoose);
                getDialog().dismiss();
            }
        });
    }

    public void getChooseString(CallBackOption callBackOption) {
        this.mCallBackOption = callBackOption;
    }

    private void updateUI() {
        mTxtTile.setText(title);
        if (mLevelRecipeAdapter == null) {
            mLevelRecipeAdapter = new BoottomSheetLevelRecipeAdapter(getContext(), mOptions);
            mRecyclerView.setAdapter(mLevelRecipeAdapter);
            mLevelRecipeAdapter.setmOnBottomSheetClickListener(new BoottomSheetLevelRecipeAdapter.OnBottomSheetClickListener() {
                @Override
                public void onCLickValue(int position) {
                    mPositionChoose = position;
                }
            });
        }else{
            mLevelRecipeAdapter.notifyDataSetChanged();
        }
    }
}
