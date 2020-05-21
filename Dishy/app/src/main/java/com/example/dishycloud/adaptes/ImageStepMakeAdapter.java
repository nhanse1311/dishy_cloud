package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.dishycloud.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageStepMakeAdapter extends PagerAdapter {
    private Context mContext;
    private String mImgeOne, mImgWto;
    private List<String> mListImage;

    public ImageStepMakeAdapter(Context mContext, String mImgeOne, String mImgWto) {
        this.mContext = mContext;
        this.mImgeOne = mImgeOne;
        this.mImgWto = mImgWto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mListImage = new ArrayList<>();
        mListImage.add(mImgeOne);
        mListImage.add(mImgWto);

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image_in_make_step_ra, null);
        ImageView imageView = view.findViewById(R.id.img_item_make_steps);
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build()
                .load(mListImage.get(position))
                .error(R.mipmap.logo).placeholder(R.mipmap.logo).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mImgWto.equals("") ? 1 : 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return o == view;
    }
}
