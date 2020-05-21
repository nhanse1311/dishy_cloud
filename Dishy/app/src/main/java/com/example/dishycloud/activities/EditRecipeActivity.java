package com.example.dishycloud.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.FragmentEditRecipeAdapter;
import com.example.dishycloud.fragments.EditOneFragment;
import com.example.dishycloud.fragments.EditTwoFragment;

public class EditRecipeActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ViewPager mViewPager ;
    private FragmentEditRecipeAdapter mEditRecipeAdapter;
    private LinearLayout mLLStepOne, mLLStepWto, mLLStepThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditOneFragment.mOnNextStepEditListener = new EditOneFragment.OnNextStepEditListener() {
            @Override
            public void onNextStepWto(int nextPage) {
                mViewPager.setCurrentItem(nextPage);
            }
        };

        EditTwoFragment.mOnStepTwoEditClickListener =  new EditTwoFragment.OnStepTwoEditClickListener() {
            @Override
            public void onClick(int pager) {
                mViewPager.setCurrentItem(pager);
            }
        };
    }

    private void initView(){
        mToolbar = findViewById(R.id.tb_edit_recipe);
        mViewPager =  findViewById(R.id.vp_edit_recipe);
        mLLStepOne = findViewById(R.id.ll_dot_step_1_edit);
        mLLStepWto = findViewById(R.id.ll_dot_step_2_edit);
        mLLStepThree = findViewById(R.id.ll_dot_step_3_edit);
    }

    private void initData(){
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditRecipeActivity.this.finish();
            }
        });

        mEditRecipeAdapter =  new FragmentEditRecipeAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mEditRecipeAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    setPageStepOne();
                }else if (position==1){
                    setPageStepWto();
                }else if (position==2){
                    setPageStepThree();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setPageStepOne(){
        mLLStepOne.setBackgroundResource(R.drawable.custom_step_over);
        mLLStepWto.setBackgroundResource(R.drawable.custom_step_over_no_selected);
        mLLStepThree.setBackgroundResource(R.drawable.custom_step_over_no_selected);
    }

    private void setPageStepWto(){
        mLLStepOne.setBackgroundResource(R.drawable.custom_step_over_no_selected);
        mLLStepWto.setBackgroundResource(R.drawable.custom_step_over);
        mLLStepThree.setBackgroundResource(R.drawable.custom_step_over_no_selected);
    }

    private void setPageStepThree(){
        mLLStepOne.setBackgroundResource(R.drawable.custom_step_over_no_selected);
        mLLStepWto.setBackgroundResource(R.drawable.custom_step_over_no_selected);
        mLLStepThree.setBackgroundResource(R.drawable.custom_step_over);
    }
}
