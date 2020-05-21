package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.models.Dishy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopDishyAdapter extends RecyclerView.Adapter<TopDishyAdapter.ViewHolder> {
    public interface OnTopDishy {
        void onClick(Dishy dishy);
    }
    private Context mContext;
    private List<Dishy> mDishyList;
    private OnTopDishy mOnTopDishy;

    public void setmOnTopDishy(OnTopDishy mOnTopDishy) {
        this.mOnTopDishy = mOnTopDishy;
    }

    public TopDishyAdapter(Context mContext, List<Dishy> mDishyList) {
        this.mContext = mContext;
        this.mDishyList = mDishyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_top_dishy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mTxtName.setText(mDishyList.get(position).getName());
        holder.mTxtTime.setText(mDishyList.get(position).getTime());
        holder.mTxtLevelDishy.setText(mDishyList.get(position).getLevel());

        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build().load(mDishyList.get(position).getImage())
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.mImgDishy);
        if (mDishyList.get(position).getStar()==1){
            holder.mImgStar1.setVisibility(View.INVISIBLE);
            holder.mImgStar2.setVisibility(View.INVISIBLE);
            holder.mImgStar3.setVisibility(View.INVISIBLE);
            holder.mImgStar4.setVisibility(View.INVISIBLE);
        }else if (mDishyList.get(position).getStar()==2){
            holder.mImgStar1.setVisibility(View.INVISIBLE);
            holder.mImgStar2.setVisibility(View.INVISIBLE);
            holder.mImgStar3.setVisibility(View.INVISIBLE);
        }else if (mDishyList.get(position).getStar()==3){
            holder.mImgStar1.setVisibility(View.INVISIBLE);
            holder.mImgStar2.setVisibility(View.INVISIBLE);
        }else if (mDishyList.get(position).getStar()==4){
            holder.mImgStar1.setVisibility(View.INVISIBLE);
        }
        holder.mLLFormInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnTopDishy.onClick(mDishyList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDishyList != null ? mDishyList.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgDishy, mImgStar1, mImgStar2, mImgStar3, mImgStar4, mImgStar5;
        private TextView mTxtName, mTxtTime,mTxtLevelDishy;
        private LinearLayout mLLFormInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLLFormInfo = itemView.findViewById(R.id.ll_form_info_top_dishy);
            mImgDishy = itemView.findViewById(R.id.img_dishy_top);
            mTxtName = itemView.findViewById(R.id.txt_name_dishy_top);
            mTxtTime = itemView.findViewById(R.id.txt_time_dishy_top);
            mImgStar1 = itemView.findViewById(R.id.img_star_top_dishy_1);
            mImgStar2 = itemView.findViewById(R.id.img_star_top_dishy_2);
            mImgStar3 = itemView.findViewById(R.id.img_star_top_dishy_3);
            mImgStar4 = itemView.findViewById(R.id.img_star_top_dishy_4);
            mImgStar5 = itemView.findViewById(R.id.img_star_top_dishy_5);
            mTxtLevelDishy = itemView.findViewById(R.id.txt_level_top_dishy);
        }
    }
}
