package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.models.Material;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder> {
    public interface OnItemMaterialClickListener {
        void onClick(Material material, int position);
    }

    private Context mContext;
    private List<Material> mMaterials;
    private OnItemMaterialClickListener mOnItemMaterialClickListener;

    public void setmOnItemMaterialClickListener(OnItemMaterialClickListener mOnItemMaterialClickListener) {
        this.mOnItemMaterialClickListener = mOnItemMaterialClickListener;
    }

    public MaterialAdapter(Context mContext, List<Material> mMaterials) {
        this.mContext = mContext;
        this.mMaterials = mMaterials;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mEdtName.setText(mMaterials.get(position).getName());
        holder.mEdtQuality.setText(String.valueOf(mMaterials.get(position).getQuality()));
        holder.mTxtUnit.setText(mMaterials.get(position).getUnit());
        holder.mTxtOrder.setText(String.valueOf(position + 2));
        holder.mLLUnit.setEnabled(false);
        holder.mImgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemMaterialClickListener.onClick(mMaterials.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMaterials != null ? mMaterials.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLLUnit;
        private EditText mEdtName, mEdtQuality;
        private TextView mTxtUnit, mTxtOrder;
        private ImageView mImgEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgEdit = itemView.findViewById(R.id.img_edit_rcv);
            mEdtName = itemView.findViewById(R.id.edt_name_nl_rv);
            mLLUnit = itemView.findViewById(R.id.ll_unit_rcv);
            mEdtQuality = itemView.findViewById(R.id.edt_quality_material_rv);
            mTxtUnit = itemView.findViewById(R.id.txt_unit_rv);
            mTxtOrder = itemView.findViewById(R.id.txt_number_rv);
        }
    }
}
