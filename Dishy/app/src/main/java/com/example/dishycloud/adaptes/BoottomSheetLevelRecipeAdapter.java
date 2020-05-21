package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.models.ChooseOptionBottomSheet;

import java.util.List;

public class BoottomSheetLevelRecipeAdapter extends RecyclerView.Adapter<BoottomSheetLevelRecipeAdapter.ViewHolder> {
    public interface OnBottomSheetClickListener {
        void onCLickValue(int position);
    }

    private Context mContext;
    private List<ChooseOptionBottomSheet> mLevelRecipes;
    private int mCheckedPostion = -1;
    private OnBottomSheetClickListener mOnBottomSheetClickListener;

    public void setmOnBottomSheetClickListener(OnBottomSheetClickListener mOnBottomSheetClickListener) {
        this.mOnBottomSheetClickListener = mOnBottomSheetClickListener;
    }

    public BoottomSheetLevelRecipeAdapter(Context mContext, List<ChooseOptionBottomSheet> mLevelRecipes) {
        this.mContext = mContext;
        this.mLevelRecipes = mLevelRecipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_bottom_sheet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mRadioButton.setChecked(position == mCheckedPostion);
        holder.mTxtValue.setText(mLevelRecipes.get(position).getName());
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnBottomSheetClickListener!= null){
                    if (position == mCheckedPostion) {
                        holder.mRadioButton.setChecked(false);
                        mCheckedPostion = -1;
                    } else {
                        mCheckedPostion = position;
                        mOnBottomSheetClickListener.onCLickValue(position);
                        notifyDataSetChanged();
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mLevelRecipes != null ? mLevelRecipes.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private RadioButton mRadioButton;
        private TextView mTxtValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLinearLayout = itemView.findViewById(R.id.lnl_root);
            mRadioButton = itemView.findViewById(R.id.rd_bottom_sheet);
            mTxtValue = itemView.findViewById(R.id.txt_bts_value);
        }
    }

}
