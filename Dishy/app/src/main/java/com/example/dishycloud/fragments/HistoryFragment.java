package com.example.dishycloud.fragments;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.HistoryAdapter;
import com.example.dishycloud.models.DataHistory;
import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.presenters.GetAllRecipeHistoryPresenter;
import com.example.dishycloud.sqlites.DatabaseHelper;
import com.example.dishycloud.views.GetAllRecipeHistoryView;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment implements View.OnClickListener, GetAllRecipeHistoryView<Recipe> {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;

    private View mView;
    private DatabaseHelper mDatabaseHelper;
    private GetAllRecipeHistoryPresenter mGetAllRecipeHistoryPresenter;
    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
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
        mView = inflater.inflate(R.layout.fragment_history, container, false);
        initView();
        initData();
        return mView;
    }

    public void initView() {
        recyclerView =  mView.findViewById(R.id.recycler_history);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initData() {
      mDatabaseHelper = new DatabaseHelper(getContext());
        // list recipe history
      mGetAllRecipeHistoryPresenter = new GetAllRecipeHistoryPresenter(this);
      mGetAllRecipeHistoryPresenter.getAllRecipeHistory(mDatabaseHelper.getToken());
    }

    private void updateRCV(List<Recipe> recipeList) {
        if (adapter == null) {
            adapter = new HistoryAdapter(getActivity().getApplicationContext(), recipeList);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(List<Recipe> recipeList) {
            updateRCV(recipeList);
    }

    @Override
    public void onFail(String message) {

    }
}
