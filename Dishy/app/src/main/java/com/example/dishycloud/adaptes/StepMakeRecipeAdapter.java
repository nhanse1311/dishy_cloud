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
import androidx.viewpager.widget.ViewPager;

import com.example.dishycloud.R;
import com.example.dishycloud.models.StepMake;

import java.util.List;

public class StepMakeRecipeAdapter extends RecyclerView.Adapter<StepMakeRecipeAdapter.ViewHolder> {
    private Context mContext;
    private List<StepMake> mStepMakes;
    private ImageStepMakeAdapter mImageStepMakeAdapter;

    public StepMakeRecipeAdapter(Context mContext, List<StepMake> mStepMakes) {
        this.mContext = mContext;
        this.mStepMakes = mStepMakes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_make_step_recipe_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mTxtOrder.setText("Bước " + (position + 1));
        holder.mTxtDescription.setText(mStepMakes.get(position).getDescrip());
        if (mStepMakes.get(position).isPrepairStage()){
            holder.mTxtRepair.setVisibility(View.VISIBLE);
        }else{
            holder.mTxtRepair.setVisibility(View.GONE);
        }
        mImageStepMakeAdapter = new ImageStepMakeAdapter(mContext,mStepMakes.get(position).getUrlImgeOne(),mStepMakes.get(position).getUrlImgWto());
        holder.mViewPager.setAdapter(mImageStepMakeAdapter);
        if (mImageStepMakeAdapter.getCount()==1){
            holder.mImgDotTwo.setVisibility(View.GONE);
        }else {
            holder.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position==0){
                        holder.mImgDotOne.setImageResource(R.drawable.ic_radio_button_checked);
                        holder.mImgDotTwo.setImageResource(R.drawable.ic_radio_button_unchecked);
                    }else{
                        holder.mImgDotOne.setImageResource(R.drawable.ic_radio_button_unchecked);
                        holder.mImgDotTwo.setImageResource(R.drawable.ic_radio_button_checked);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mStepMakes != null ? mStepMakes.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtOrder, mTxtDescription,mTxtRepair;
        private ViewPager mViewPager;
        private LinearLayout mLLDots;
        private ImageView mImgDotOne,mImgDotTwo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtRepair  = itemView.findViewById(R.id.txt_repair);
            mTxtOrder = itemView.findViewById(R.id.txt_order_make_step_rcv_ra);
            mTxtDescription = itemView.findViewById(R.id.txt_make_step_des_rcv);
            mViewPager = itemView.findViewById(R.id.vp_step_make_rcv_ra);
            mLLDots = itemView.findViewById(R.id.ll_dots_rcv);
            mImgDotOne = itemView.findViewById(R.id.img_dots_image_one_rcv);
            mImgDotTwo = itemView.findViewById(R.id.img_dots_image_wto_rcv);
        }
    }
}
