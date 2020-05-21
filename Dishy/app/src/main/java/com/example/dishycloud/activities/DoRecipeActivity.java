package com.example.dishycloud.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.DoRecipeAdapter;
import com.example.dishycloud.models.StepMake;

import java.util.List;

public class DoRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private List<StepMake> mStepMakes;
    private DoRecipeAdapter mDoRecipeAdapter;
    private ViewPager mViewPager;
    private TextView mTxtNumberPage;
    private Toolbar mToolbar;
    private ImageView mImgPre, mImgNext;
    private boolean check = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_recipe);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = findViewById(R.id.vp_do_recipe);
        mToolbar = findViewById(R.id.tb_do_recipe);
        mTxtNumberPage = findViewById(R.id.txt_step_number);
        mImgNext = findViewById(R.id.img_move_next);
        mImgPre = findViewById(R.id.img_move_back);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initData() {
        mImgNext.setOnClickListener(this);
        mImgPre.setOnClickListener(this);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        mToolbar.setTitle(name);
        mStepMakes = (List<StepMake>) intent.getSerializableExtra("STEP");

        mDoRecipeAdapter = new DoRecipeAdapter(this, mStepMakes);
        mViewPager.setAdapter(mDoRecipeAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTxtNumberPage.setText(String.valueOf(position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_move_next:
                int numberNext = Integer.parseInt(mTxtNumberPage.getText().toString().trim());
                if (numberNext <= mStepMakes.size()-1) {
                    mViewPager.setCurrentItem(numberNext);
                    mTxtNumberPage.setText(String.valueOf(numberNext + 1));
                    if (numberNext == mStepMakes.size() - 1) {
                        mImgNext.setImageResource(R.mipmap.ic_edit_recipe_done);
                        check = true;
                    }
                }
                if (check == true&&numberNext==mStepMakes.size()) {
                    Dialog dialog = new Dialog(DoRecipeActivity.this,R.style.Theme_Dialog);
                    dialog.setContentView(R.layout.dialog_success_cook);
                    dialog.show();
                    Button button = dialog.findViewById(R.id.btn_ok_success_dialog_cook);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
                }
                break;
            case R.id.img_move_back:
                int numberBack = Integer.parseInt(mTxtNumberPage.getText().toString().trim());
                if (numberBack > 1) {
                    mViewPager.setCurrentItem(numberBack-2);
                    mTxtNumberPage.setText(String.valueOf(numberBack-1));
                }
                break;
        }
    }
}
