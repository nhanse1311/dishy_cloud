package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.models.Chef;
import com.example.dishycloud.models.User;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FollowChefApdater extends RecyclerView.Adapter<FollowChefApdater.ViewHolder> {
    public interface  OnFollowChefClickListener{
        void onClick(Chef chef);
    }
    private Context mContext;
    private List<User> mChefs;
    private OnFollowChefClickListener mOnFollowChefClickListener;

    public void setmOnFollowChefClickListener(OnFollowChefClickListener mOnFollowChefClickListener) {
        this.mOnFollowChefClickListener = mOnFollowChefClickListener;
    }

    public FollowChefApdater(Context mContext, List<User> mChefs) {
        this.mContext = mContext;
        this.mChefs = mChefs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_follow_chef, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build().load(mChefs.get(position).getAvartar())
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.mAvatar);
        holder.mTxtName.setText(mChefs.get(position).getFullname());
        holder.mTxtNumberRecipe.setText(mChefs.get(position).getNumberRecipe() + " công thức");
        holder.mTxtNumberPeopleLike.setText(mChefs.get(position).getNumberFollower()+ " người theo dõi");

        holder.mTxtPositionChefFollow.setText(String.valueOf(position + 1));

    }

    @Override
    public int getItemCount() {
        return mChefs != null ? mChefs.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLLRoot;
        private RoundedImageView mAvatar;
        private TextView mTxtName, mTxtNumberRecipe, mTxtNumberPeopleLike, mTxtPositionChefFollow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAvatar = itemView.findViewById(R.id.riv_avatar_chef);
            mTxtName = itemView.findViewById(R.id.txt_name_chef);
            mTxtNumberRecipe = itemView.findViewById(R.id.txt_numer_recipes_follow);
            mTxtNumberPeopleLike = itemView.findViewById(R.id.txt_numer_like);
            mLLRoot = itemView.findViewById(R.id.ll_follow_chef);
            mTxtPositionChefFollow = itemView.findViewById(R.id.txt_position_chef_follow);
        }
    }
}
