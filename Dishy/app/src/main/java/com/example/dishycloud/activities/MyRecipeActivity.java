package com.example.dishycloud.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.MaterialRecipeAdapter;
import com.example.dishycloud.adaptes.StepMakeRecipeAdapter;
import com.example.dishycloud.models.Dishy;
import com.example.dishycloud.models.Material;
import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.StepMake;
import com.example.dishycloud.presenters.DoRecipePresenter;
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.views.DoRecipeView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyRecipeActivity extends AppCompatActivity implements View.OnClickListener, DoRecipeView {
    private TextView mTxtNumberCount, mTxtNameRecipe, mTxtDecription,mTxtFavorite;
    private Button mBtnDiv, mBtnSum, mBtnDoRecipe;
    private ImageView mImgAvatarRecipe, mImgFavorite, mImgBack, mImgDelete, mImgUpdate;
    private Toolbar mToolbar;
    private NestedScrollView mNestedScrollView;

    private RecyclerView mRcvMaterial, mRcvStepMake;
    private MaterialRecipeAdapter materialRecipeAdapter;
    private StepMakeRecipeAdapter mStepMakeRecipeAdapter;
    private List<Material> mMaterials;
    private List<StepMake> mStepMakes;

    private int numberCount;
    private int numberEater;
    private Recipe recipe;

    private DoRecipePresenter mDoRecipePresenter;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe);
        initView();
        initData();
    }

    private void initView() {
        mRcvMaterial = findViewById(R.id.rcv_material_my_recipe_activity);
        LinearLayoutManager manager = new LinearLayoutManager(MyRecipeActivity.this, RecyclerView.VERTICAL, false);
        mRcvMaterial.setHasFixedSize(true);
        mRcvMaterial.setLayoutManager(manager);

        mRcvStepMake = findViewById(R.id.rcv_make_step_mra);
        LinearLayoutManager magagerStepMake = new LinearLayoutManager(MyRecipeActivity.this, RecyclerView.VERTICAL, false);
        mRcvStepMake.setLayoutManager(magagerStepMake);
        mRcvStepMake.setHasFixedSize(true);

        mTxtNumberCount = findViewById(R.id.txt_number_eater_mra);
        mBtnDiv = findViewById(R.id.btn_div_mra);
        mBtnSum = findViewById(R.id.btn_sum_mra);
        mImgAvatarRecipe = findViewById(R.id.img_avatar_food_mra);
        mImgFavorite = findViewById(R.id.img_favorite_mra);
        mTxtNameRecipe = findViewById(R.id.txt_name_recipe_mra);
        mNestedScrollView = findViewById(R.id.nsv__my_recipe_activity);
        mImgBack = findViewById(R.id.img_button_back_my_recipe_activity);
        mToolbar = findViewById(R.id.tb_mra);
        mTxtFavorite = findViewById(R.id.txt_number_favorite_recipe_mra);
        mBtnDoRecipe = findViewById(R.id.btn_do_recipe_mra);
        mImgDelete = findViewById(R.id.img_button_remove);
        mImgUpdate = findViewById(R.id.img_button_edit);
        mTxtDecription = findViewById(R.id.txt_decription_mc);
    }

    private void initData() {
        mBtnDiv.setOnClickListener(this);
        mBtnSum.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
        mImgFavorite.setOnClickListener(this);
        mBtnDoRecipe.setOnClickListener(this);
        mImgUpdate.setOnClickListener(this);
        mImgDelete.setOnClickListener(this);

        recipe = (Recipe) getIntent().getSerializableExtra("MYDISHY");

        Picasso.Builder builder = new Picasso.Builder(MyRecipeActivity.this);
        builder.build().load(recipe.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(mImgAvatarRecipe);

        mTxtFavorite.setText(String.valueOf(recipe.getLiked()));
        mTxtNameRecipe.setText(recipe.getName());
        mTxtDecription.setText(recipe.getDescription());

        mMaterials = new ArrayList<>();
        mMaterials = recipe.getMaterials();

        numberEater = recipe.getNumberPeople();
        mTxtNumberCount.setText(String.valueOf(numberEater));
        numberCount = Integer.parseInt(mTxtNumberCount.getText().toString().trim());

        updateRcvMaterial(numberCount, numberEater);
        mStepMakes = new ArrayList<>();
        mStepMakes = recipe.getSteps();
        updateRcvStepMake();

        mToolbar.setTitle(recipe.getName());
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 150) {
                    mToolbar.setVisibility(View.VISIBLE);
                } else {
                    mToolbar.setVisibility(View.GONE);
                }
            }
        });

        mDoRecipePresenter = new DoRecipePresenter(this);
        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
    }

    private void showDialogRemove() {
        final Dialog dialog = new Dialog(MyRecipeActivity.this, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_remove_question);
        dialog.show();

        Button buttonYes = dialog.findViewById(R.id.btn_confirm_remove);
        Button buttonNo = dialog.findViewById(R.id.btn_no_confirm_remove);

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyRecipeActivity.this.finish();
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void updateRcvMaterial(int numberCount, int numberEater) {
        if (materialRecipeAdapter == null) {
            materialRecipeAdapter = new MaterialRecipeAdapter(MyRecipeActivity.this, mMaterials, numberEater, numberCount);
            mRcvMaterial.setAdapter(materialRecipeAdapter);
        } else {
            materialRecipeAdapter.update(numberCount);
        }
    }

    private void updateRcvStepMake() {
        if (mStepMakeRecipeAdapter == null) {
            mStepMakeRecipeAdapter = new StepMakeRecipeAdapter(MyRecipeActivity.this, mStepMakes);
            mRcvStepMake.setAdapter(mStepMakeRecipeAdapter);
        } else {
            mStepMakeRecipeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_div_mra:
                int number = Integer.parseInt(mTxtNumberCount.getText().toString().trim());
                int afterDiv = number;
                if (afterDiv > 1) {
                    afterDiv = afterDiv - 1;
                    mTxtNumberCount.setText(String.valueOf(afterDiv));
                }
                updateRcvMaterial(afterDiv, numberEater);
                break;

            case R.id.btn_sum_mra:
                int numberSum = Integer.parseInt(mTxtNumberCount.getText().toString().trim());
                int afterSum = numberSum + 1;
                mTxtNumberCount.setText(String.valueOf(afterSum));
                updateRcvMaterial(afterSum, numberEater);
                break;

            case R.id.img_button_back_my_recipe_activity:
                finish();
                break;

            case R.id.btn_do_recipe:
                mDoRecipePresenter.doRecipe(mDatabaseHelper.getToken(), recipe.getId());
                break;

            case R.id.img_button_remove:
                showDialogRemove();
                break;
            case R.id.img_button_edit:
                Intent moveEdit = new Intent(MyRecipeActivity.this,EditRecipeActivity.class);
                moveEdit.putExtra("EDIT",recipe);
                MyRecipeActivity.this.startActivity(moveEdit);
                break;
        }
    }

    @Override
    public void onDoRecipeSuccess(String message) {
        Intent intent = new Intent(MyRecipeActivity.this, DoRecipeActivity.class);
        intent.putExtra("NAME", recipe.getName());
        intent.putExtra("STEP", (Serializable) recipe.getSteps());
        startActivity(intent);
    }

    @Override
    public void onDoRecipeFail(String message) {

    }
}
