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
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    public interface OnAlbumClickListener{
        void OnClick(Dishy dishy);
    }

    private Context mContext;
    private List<Dishy> mDishies;
    private boolean mIsMyAlbum;
    private OnAlbumClickListener mOnAlbumClickListener;

    public void setmOnAlbumClickListener(OnAlbumClickListener mOnAlbumClickListener) {
        this.mOnAlbumClickListener = mOnAlbumClickListener;
    }

    public AlbumAdapter(Context context, List<Dishy> dishies, boolean isMyAlbum) {
        mContext = context;
        mDishies = dishies;
        mIsMyAlbum = isMyAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_saved_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mTxtName.setText(mDishies.get(position).getName());
        holder.mTxtTime.setText(mDishies.get(position).getTime());
        holder.mTxtOrderRecipe.setText(String.valueOf(position+1));

        if (mIsMyAlbum) {
            holder.mTxtOption.setText("Chỉnh sửa");
        } else {
            holder.mTxtOption.setText("Bỏ theo dõi");
        }


        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build()
                .load(mDishies.get(position).getImage())
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.mImageFood);
        holder.mImageFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAlbumClickListener.OnClick(mDishies.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDishies != null ? mDishies.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtName, mTxtTime, mTxtOrderRecipe, mTxtOption;
        private ImageView mImageFood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtOrderRecipe = itemView.findViewById(R.id.txt_order_recipe_ca_save);
            mTxtTime = itemView.findViewById(R.id.txt_time_save);
            mTxtName = itemView.findViewById(R.id.txt_name_recipe_chef_save);
            mImageFood = itemView.findViewById(R.id.img_recipe_chef_save);
            mTxtOption = itemView.findViewById(R.id.txt_option_save);
        }
    }
}
