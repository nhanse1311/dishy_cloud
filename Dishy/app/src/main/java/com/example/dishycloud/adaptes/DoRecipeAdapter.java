package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.dishycloud.R;
import com.example.dishycloud.models.StepMake;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DoRecipeAdapter extends PagerAdapter {
    private Context mContext;
    private List<StepMake> mStepMakes;
    public ImageView mImgMain, mImgDotOne, mImgDotWto;
    private TextView mTxtPrepair, mTxtDesc;

    public DoRecipeAdapter(Context mContext, List<StepMake> mStepMakes) {
        this.mContext = mContext;
        this.mStepMakes = mStepMakes;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_do_recipe_activity, null);
        mImgMain = view.findViewById(R.id.img_do_recipe);
        mImgDotOne = view.findViewById(R.id.img_dot_one);
        mImgDotWto = view.findViewById(R.id.img_dot_wto);
        mTxtPrepair = view.findViewById(R.id.txt_repair_do);
        mTxtDesc = view.findViewById(R.id.txt_make_step_des_rcv_do);

        mTxtDesc.setText(mStepMakes.get(position).getDescrip());
        if (mStepMakes.get(position).isPrepairStage()) {
            mTxtPrepair.setVisibility(View.VISIBLE);
        } else {
            mTxtPrepair.setVisibility(View.GONE);
        }

        final Picasso.Builder builder = new Picasso.Builder(mContext);

        builder.build()
                .load(mStepMakes.get(position).getUrlImgeOne())
                .error(R.mipmap.logo).placeholder(R.mipmap.logo).into(mImgMain);

        builder.build()
                .load(mStepMakes.get(position).getUrlImgeOne())
                .error(R.mipmap.logo).placeholder(R.mipmap.logo).into(mImgDotOne);


        builder.build()
                .load(mStepMakes.get(position).getUrlImgWto())
                .error(R.mipmap.logo).placeholder(R.mipmap.logo).into(mImgDotWto);

        container.addView(view);

       mImgDotOne.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               builder.build()
                       .load(mStepMakes.get(position).getUrlImgeOne())
                       .error(R.mipmap.logo).placeholder(R.mipmap.logo).into(mImgMain);
               notifyDataSetChanged();
           }
       });

       mImgMain.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               builder.build()
                       .load(mStepMakes.get(position).getUrlImgWto())
                       .error(R.mipmap.logo).placeholder(R.mipmap.logo).into(mImgMain);
               notifyDataSetChanged();
           }
       });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mStepMakes.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return o == view;
    }
}
