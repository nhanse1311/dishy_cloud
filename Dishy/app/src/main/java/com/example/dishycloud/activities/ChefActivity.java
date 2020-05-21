package com.example.dishycloud.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.RecipeOfChefAdapter;
import com.example.dishycloud.bottomSheets.BottomSheetChooseOption;
import com.example.dishycloud.bottomSheets.CallBackOption;
import com.example.dishycloud.models.Chef;
import com.example.dishycloud.models.ChooseOptionBottomSheet;
import com.example.dishycloud.models.Dishy;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChefActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLLCoverWhite;
    private ImageView mImgCover, mImgButtonBack, mImgFillter;
    private RoundedImageView mAvatar;
    private TextView mTxtNumberLiker, mTxtNumberRecipe, mTxtNameChef;
    private RecyclerView mRcvRecipe;
    private Chef mChef;
    private RecipeOfChefAdapter mRecipeOfChefAdapter;
    private Toolbar mToolbar;
    private NestedScrollView mScrollView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);
        initView();
        initData();
    }

    private void initView() {
        mToolbar = findViewById(R.id.tb_chef_activity);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mScrollView = findViewById(R.id.scv_cheff_activity);
        mImgButtonBack = findViewById(R.id.img_cover_back_butotn);
        mLLCoverWhite = findViewById(R.id.ll_cover_chef_white);
        mImgCover = findViewById(R.id.ll_cover_chef);
        mTxtNameChef = findViewById(R.id.txt_name_chef_activity);
        mTxtNumberLiker = findViewById(R.id.txt_number_liker_activity);
        mTxtNumberRecipe = findViewById(R.id.txt_number_recipe_activity);
        mAvatar = findViewById(R.id.riv_avatar_chef_activity);
        mImgFillter = findViewById(R.id.img_fillter_ca);

        mRcvRecipe = findViewById(R.id.rcv_recip_of_chef);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(ChefActivity.this, 2);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(ChefActivity.this, RecyclerView.VERTICAL, false);
        mRcvRecipe.setLayoutManager(gridLayoutManager);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mChef = (Chef) intent.getSerializableExtra("CHEF");
            updateUI(mChef);
        }

        mImgButtonBack.setOnClickListener(this);
        mImgFillter.setOnClickListener(this);

        mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (i1>100){
                    mToolbar.setVisibility(View.VISIBLE);
                }else{
                    mToolbar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void updateUI(Chef chef) {
        Picasso.Builder builder = new Picasso.Builder(ChefActivity.this);
        builder.build().load(chef.getAvatar())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground).into(mImgCover);
        builder.build().load(chef.getAvatar())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground).into(mAvatar);
        mTxtNameChef.setText(chef.getName());
        mToolbar.setTitle(chef.getName());
        mTxtNumberLiker.setText(chef.getNumberLiker() + " người theo dõi");
        mTxtNumberRecipe.setText(chef.getNumberRecipes() + " công thức");
        updateRcvRecipeOfChef(chef.getDishyList());
    }

    private void updateRcvRecipeOfChef(List<Dishy> dishies) {
        if (mRecipeOfChefAdapter == null) {
            mRecipeOfChefAdapter = new RecipeOfChefAdapter(ChefActivity.this, dishies);
            mRcvRecipe.setAdapter(mRecipeOfChefAdapter);
        } else {
            mRecipeOfChefAdapter.notifyDataSetChanged();
        }
    }

    private void showBottomSheetFillter() {
        List<ChooseOptionBottomSheet> listSort = new ArrayList<>();
        listSort.add(new ChooseOptionBottomSheet(1, "Theo ngày đăng gần nhất"));
        listSort.add(new ChooseOptionBottomSheet(2, "Theo ngày đăng xa nhất"));
        listSort.add(new ChooseOptionBottomSheet(3, "Theo công thức được yêu thích nhiều nhất"));
        listSort.add(new ChooseOptionBottomSheet(4, "Theo công thức được yêu thích ít nhất"));
        BottomSheetChooseOption bottomSheetChooseOption = new BottomSheetChooseOption(ChefActivity.this, "Sắp xếp", listSort);
        bottomSheetChooseOption.show(getSupportFragmentManager(), "sortOption");
        bottomSheetChooseOption.getChooseString(new CallBackOption() {
            @Override
            public void chooseOption(ChooseOptionBottomSheet method, int position) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_cover_back_butotn:
                finish();
                break;
            case R.id.img_fillter_ca:
                showBottomSheetFillter();
                break;
        }
    }
}
