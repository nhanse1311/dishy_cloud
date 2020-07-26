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
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeOfChefAdapter extends RecyclerView.Adapter<RecipeOfChefAdapter.ViewHolder> {
    public interface OnRecipeOfChefClickListener{
        void onClick(Recipe recipe);
    }

    private Context mContext;
    private List<Recipe> mDishyList;
    private OnRecipeOfChefClickListener mOnRecipeOfChefClickListener;

    public void setmOnRecipeOfChefClickListener(OnRecipeOfChefClickListener mOnRecipeOfChefClickListener) {
        this.mOnRecipeOfChefClickListener = mOnRecipeOfChefClickListener;
    }

    public RecipeOfChefAdapter(Context mContext, List<Recipe> mDishyList) {
        this.mContext = mContext;
        this.mDishyList = mDishyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_recipe_of_chef, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mtxtOrder.setText(String.valueOf(position+1));
        holder.mtxtName.setText(mDishyList.get(position).getName());

        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build()
                .load(mDishyList.get(position).getImage()).error(R.drawable.ic_launcher_foreground).placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.mImgRecipe);

        holder.mImgRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnRecipeOfChefClickListener.onClick(mDishyList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDishyList != null ? mDishyList.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mtxtOrder,mtxtName;
        private ImageView mImgRecipe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtxtOrder = itemView.findViewById(R.id.txt_order_recipe_ca);
            mtxtName = itemView.findViewById(R.id.txt_name_recipe_chef);
            mImgRecipe = itemView.findViewById(R.id.img_recipe_chef);
        }
    }
}
