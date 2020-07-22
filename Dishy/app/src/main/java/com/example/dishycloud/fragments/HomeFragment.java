package com.example.dishycloud.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dishycloud.R;
import com.example.dishycloud.activities.ChefActivity;
import com.example.dishycloud.activities.RecipeActivity;
import com.example.dishycloud.adaptes.DishyTodayAdapter;
import com.example.dishycloud.adaptes.TopChefAdapter;
import com.example.dishycloud.adaptes.TopDishyAdapter;
import com.example.dishycloud.adaptes.TopFollowAdapter;
import com.example.dishycloud.models.Chef;
import com.example.dishycloud.models.Dishy;
import com.example.dishycloud.models.Material;
import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.StepMake;
import com.example.dishycloud.models.User;
import com.example.dishycloud.presenters.GetAllRecipeSuggestionPresenter;
import com.example.dishycloud.presenters.GetAllRecipeTopPresenter;
import com.example.dishycloud.presenters.GetTopChefPresenter;
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.views.GetAllRecipeSuggestionView;
import com.example.dishycloud.views.GetAllRecipeTopView;
import com.example.dishycloud.views.GetTopChefView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener,
        GetAllRecipeSuggestionView<Recipe>,
        GetAllRecipeTopView<Recipe>,
        GetTopChefView<User> {

    private RecyclerView mRcvDishyToday, mRcvTopDishy, mRcvTopChef, mRcvTopDishyFollow;
    private DishyTodayAdapter mDishyTodayAdapter;
    private TopDishyAdapter mTopDishyAdapter;
    private TopChefAdapter mTopChefAdapter;
    private TopFollowAdapter mTopFollowAdapter;
    private View mView;
    private DatabaseHelper mDatabaseHelper;
    private GetAllRecipeSuggestionPresenter getAllRecipeSuggestionPresenter;
    private GetAllRecipeTopPresenter getAllRecipeTopPresenter;
    private GetTopChefPresenter getTopChefPresenter;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        initData();
        return mView;
    }

    private void initView() {
        // list dish suggest
        mRcvDishyToday = mView.findViewById(R.id.rcv_dishy_today);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mRcvDishyToday.setLayoutManager(manager);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        mRcvTopDishy = mView.findViewById(R.id.rcv_top_recipe);
        mRcvTopDishy.setLayoutManager(layoutManager);

        LinearLayoutManager managerChef = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        mRcvTopChef = mView.findViewById(R.id.rcv_top_chef);
        mRcvTopChef.setLayoutManager(managerChef);

        GridLayoutManager layoutManagerFollow = new GridLayoutManager(getContext(),2);
        mRcvTopDishyFollow = mView.findViewById(R.id.rcv_top_recipe_follow);
        mRcvTopDishyFollow.setLayoutManager(layoutManagerFollow);

    }

    private void initData() {
        mDatabaseHelper = new DatabaseHelper(getContext());

        //list recipe suggestion today
        getAllRecipeSuggestionPresenter = new GetAllRecipeSuggestionPresenter(this);
        getAllRecipeSuggestionPresenter.getAllRecipeSuggestion(mDatabaseHelper.getToken());
        //list recipe top
        getAllRecipeTopPresenter = new GetAllRecipeTopPresenter(this);
        getAllRecipeTopPresenter.getAllRecipeTop(mDatabaseHelper.getToken());
        //list chef top
        getTopChefPresenter =new GetTopChefPresenter(this);
        getTopChefPresenter.getTopChef(mDatabaseHelper.getToken());



    }


    private void updateUIRcvDishyFollow(List<Dishy> followList){
        if (mTopFollowAdapter == null) {
            mTopFollowAdapter = new TopFollowAdapter(getContext(), followList);
            mRcvTopDishyFollow.setAdapter(mTopFollowAdapter);
            mTopFollowAdapter.setmOnDishyFollowClickListener(new TopFollowAdapter.OnDishyFollowClickListener() {
                @Override
                public void onClick(Dishy dishy) {
                    Intent intent = new Intent(getContext(), RecipeActivity.class);
                    intent.putExtra("DISHY",dishy);
                    startActivity(intent);
                }
            });
        } else {
            mDishyTodayAdapter.notifyDataSetChanged();
        }
    }

    private void updateUIRcvDishyToDay(List<Recipe> recipeList) {
        if (mDishyTodayAdapter == null) {
            mDishyTodayAdapter = new DishyTodayAdapter(getContext(), recipeList);
            mRcvDishyToday.setAdapter(mDishyTodayAdapter);
            mDishyTodayAdapter.setmOnDishyToDayClickListener(new DishyTodayAdapter.OnDishyToDayClickListener() {
                @Override
                public void onClick(Recipe recipe) {
                    Intent intent = new Intent(getContext(), RecipeActivity.class);
                    intent.putExtra("RECIPE",recipe);
                    startActivity(intent);
                }

            });
        } else {
            mDishyTodayAdapter.notifyDataSetChanged();
        }
    }

    private void updateUIRcvTopDishy(List<Recipe> topDishy){
        if (mTopDishyAdapter==null){
            mTopDishyAdapter =  new TopDishyAdapter(getContext(),topDishy);
            mRcvTopDishy.setAdapter(mTopDishyAdapter);
            mTopDishyAdapter.setmOnTopDishy(new TopDishyAdapter.OnTopDishy() {
                @Override
                public void onClick(Recipe recipe) {
                    Intent intent = new Intent(getContext(), RecipeActivity.class);
                    intent.putExtra("RECIPE", recipe);
                    startActivity(intent);
                }
            });
        }else{
            mTopDishyAdapter.notifyDataSetChanged();
        }
    }

    private void updateUIRcvTopChef(List<User> chefs){
        if (mTopChefAdapter==null){
            mTopChefAdapter =  new TopChefAdapter(getContext(),chefs);
            mRcvTopChef.setAdapter(mTopChefAdapter);
            mTopChefAdapter.setmOnTopChefClickListener(new TopChefAdapter.OnTopChefClickListener() {
                @Override
                public void onClick(User user) {
                    Intent intent = new Intent(getContext(), ChefActivity.class);
                    intent.putExtra("CHEF",user);
                    startActivity(intent);
                }
            });
        }else{
            mTopChefAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(List<Recipe> list) {
        updateUIRcvDishyToDay(list);
        updateUIRcvTopDishy(list);
    }

    @Override
    public void onSuccessChef(List<User> chefList) {
        updateUIRcvTopChef(chefList);
    }

    @Override
    public void onFail(String message) {

    }

}
