package com.example.dishycloud.adaptes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.models.Chef;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopChefAdapter extends RecyclerView.Adapter<TopChefAdapter.ViewHolder> {
    public interface  OnTopChefClickListener{
        void onClick(Chef chef);
    }
    private Context mContext;
    private List<Chef> mChefs;
    private OnTopChefClickListener mOnTopChefClickListener;

    public void setmOnTopChefClickListener(OnTopChefClickListener mOnTopChefClickListener) {
        this.mOnTopChefClickListener = mOnTopChefClickListener;
    }

    public TopChefAdapter(Context mContext, List<Chef> mChefs) {
        this.mContext = mContext;
        this.mChefs = mChefs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_top_chef, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build().load(mChefs.get(position).getAvatar())
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.mAvatar);
        holder.mtxtName.setText(mChefs.get(position).getName());
        holder.mTxtNumberRecipe.setText(mChefs.get(position).getNumberRecipes() + " công thức");
        holder.mTxtOrder.setText(String.valueOf(position + 1));
        holder.mTxtNumberPeopleLike.setText(mChefs.get(position).getNumberLiker()+ " người thích");
        if (position == 0) {
            holder.mAvatar.setBorderColor(Color.parseColor("#C5392F"));
            holder.mTxtOrder.setBackgroundResource(R.drawable.custom_order_top_1);
        }
        if (position == 1) {
            holder.mAvatar.setBorderColor(Color.parseColor("#FF9800"));
            holder.mTxtOrder.setBackgroundResource(R.drawable.custom_order_top_2);
        }
        if (position == 2) {
            holder.mAvatar.setBorderColor(Color.parseColor("#FFC107"));
            holder.mTxtOrder.setBackgroundResource(R.drawable.custom_order_top_3);
        }
        if (position == 3) {
            holder.mAvatar.setBorderColor(Color.parseColor("#2196F3"));
            holder.mTxtOrder.setBackgroundResource(R.drawable.custom_order_top_4);
        }
        if (position == 4) {
            holder.mAvatar.setBorderColor(Color.parseColor("#8BC34A"));
            holder.mTxtOrder.setBackgroundResource(R.drawable.custom_order_top_5);
        }
        //Set onclick
        holder.mLLRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnTopChefClickListener.onClick(mChefs.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChefs != null ? mChefs.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLLRoot;
        private RoundedImageView mAvatar;
        private TextView mtxtName, mTxtNumberRecipe, mTxtOrder,mTxtNumberPeopleLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAvatar = itemView.findViewById(R.id.riv_avatar_chef);
            mtxtName = itemView.findViewById(R.id.txt_name_chef);
            mTxtNumberRecipe = itemView.findViewById(R.id.txt_numer_recipes);
            mTxtOrder = itemView.findViewById(R.id.txt_order_chef);
            mTxtNumberPeopleLike = itemView.findViewById(R.id.txt_numer_like);
            mLLRoot = itemView.findViewById(R.id.ll_top_chef);
        }
    }
}
