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

import java.util.ArrayList;


public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;

    private View mView;

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
        ArrayList<DataHistory> dataNotifications = new ArrayList<>();
        dataNotifications.add(
            new DataHistory("https://images.pexels.com/photos/1410236/pexels-photo-1410236.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", "Cơm Trộn Trứng", "70 like", "20 phút", "Dễ", "Hoàn thành","11/10/2019 12:50:45",R.drawable.ic_favorite_border_black));
        dataNotifications.add(
                new DataHistory("https://images.pexels.com/photos/1464601/pexels-photo-1464601.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", "Salad Trộn", "30 like", "15 phút", "Dễ", "Hoàn thành","11/10/2019 7:50:45",  R.drawable.ic_favorite_red));
        dataNotifications.add(
                new DataHistory("https://images.pexels.com/photos/1146760/pexels-photo-1146760.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", "Pizza Hải Sản", "40 like", "60 phút", "Vừa", "Hoàn thành","10/10/2019 14:30:30",  R.drawable.ic_favorite_red));
        dataNotifications.add(
                new DataHistory("https://images.pexels.com/photos/36768/food-salmon-teriyaki-cooking.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500", "Cá Hồi Sashimi", "100 like", "30 phút", "Vừa", "Hoàn thành","9/10/2019 8:20:45", R.drawable.ic_favorite_border_black));
        dataNotifications.add(
                new DataHistory("https://images.pexels.com/photos/161674/appetite-beef-big-bread-161674.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", "Hamburger Bò", "170 like", "25 phút", "Dễ", "Hoàn thành","8/10/2019 13:50:45", R.drawable.ic_favorite_border_black));
        dataNotifications.add(
                new DataHistory("https://images.pexels.com/photos/46247/thai-food-noodle-fried-noodles-meal-46247.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", "Phở Xào Đậu Hủ", "50 like", "20 phút", "Khó", "Hoàn thành","6/10/2019 12:20:45", R.drawable.ic_favorite_red));
        dataNotifications.add(
                new DataHistory("https://images.pexels.com/photos/1860202/pexels-photo-1860202.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", "Tôm Chiên Bột", "120 like", "30 phút", "Dễ", "Hoàn thành","4/10/2019 11:50:45", R.drawable.ic_favorite_red));
        dataNotifications.add(
                new DataHistory("https://images.pexels.com/photos/2059153/pexels-photo-2059153.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", "Bún Thịt Nướng", "200 like", "40 phút", "Vừa", "Hoàn thành","2/10/2019 12:50:45", R.drawable.ic_favorite_red));
        updateRCV(dataNotifications);
    }

    private void updateRCV(ArrayList<DataHistory> notifications) {
        if (adapter == null) {
            adapter = new HistoryAdapter(getActivity().getApplicationContext(), notifications);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
