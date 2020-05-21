package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.models.Material;

import java.util.List;

public class MaterialRecipeAdapter extends RecyclerView.Adapter<MaterialRecipeAdapter.ViewHolder> {
    private Context mContext;
    private List<Material> mMaterialList;
    private int mNumberEater;
    private int mNumberCount;


    public MaterialRecipeAdapter(Context mContext, List<Material> mMaterialList, int mNumberEater, int mNumberCount) {
        this.mContext = mContext;
        this.mMaterialList = mMaterialList;
        this.mNumberEater = mNumberEater;
        this.mNumberCount = mNumberCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_material_recipe_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTxtOrder.setText(String.valueOf(position + 1));
        holder.mTxtName.setText(mMaterialList.get(position).getName());
        double quality = Math.ceil(10*((mMaterialList.get(position).getQuality()/mNumberEater)*mNumberCount))/10;
        holder.mTxtNumber.setText(String.valueOf(quality));
        holder.mTxtUnit.setText(mMaterialList.get(position).getUnit());
    }

    public void update(int numberCount){
        this.mNumberCount = numberCount;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMaterialList != null ? mMaterialList.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtOrder, mTxtName, mTxtNumber, mTxtUnit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtOrder = itemView.findViewById(R.id.txt_order_material_ra);
            mTxtName = itemView.findViewById(R.id.txt_name_ra);
            mTxtNumber = itemView.findViewById(R.id.txt_quality_ra);
            mTxtUnit = itemView.findViewById(R.id.txt_unit_material_ra);
        }
    }
}
