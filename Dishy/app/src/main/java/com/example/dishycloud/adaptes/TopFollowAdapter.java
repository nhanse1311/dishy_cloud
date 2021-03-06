package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.models.Dishy;
import com.example.dishycloud.models.Recipe;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopFollowAdapter extends RecyclerView.Adapter<TopFollowAdapter.ViewHolder>{

    public interface OnDishyFollowClickListener{
        void onClick(Recipe recipe);
    }

    private Context mContext;
    private List<Recipe> mRecipeList;
    private OnDishyFollowClickListener mOnDishyFollowClickListener;

    public void setmOnDishyFollowClickListener(OnDishyFollowClickListener mOnDishyFollowClickListener){
        this.mOnDishyFollowClickListener = mOnDishyFollowClickListener;
    }

    public TopFollowAdapter(Context mContext, List<Recipe> mRecipeList){
        this.mContext = mContext;
        this.mRecipeList = mRecipeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_dishy_follow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mTxtName.setText(mRecipeList.get(position).getName());

        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build().
                load(mRecipeList.get(position).getImage()).error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.mImgFood);
        builder.build().
                load(mRecipeList.get(position).getImage()).error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.mImgAvatar);
        holder.mImgFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnDishyFollowClickListener.onClick(mRecipeList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipeList != null ? mRecipeList.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImgFood;
        private RoundedImageView mImgAvatar;
        private TextView mTxtName, mTxtTime, mTxtUsername;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mImgFood = itemView.findViewById(R.id.img_dishy_follow);
            mImgAvatar = itemView.findViewById(R.id.avatar_follow);
            mTxtName = itemView.findViewById(R.id.txt_name_dishy_follow);
            mTxtTime = itemView.findViewById(R.id.txt_time_dishy_follow);
            mTxtUsername = itemView.findViewById(R.id.loginName_follow);
        }
    }
}
