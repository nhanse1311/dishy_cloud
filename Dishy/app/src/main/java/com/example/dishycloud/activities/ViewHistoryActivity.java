package com.example.dishycloud.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.SearchAdapter;
import com.example.dishycloud.models.Dishy;
import com.example.dishycloud.models.ItemsResult;

import java.util.ArrayList;
import java.util.List;

public class ViewHistoryActivity extends AppCompatActivity {

    private List<ItemsResult> itemsResults;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        initView();
        initData();
    }

    private void initData() {
        itemsResults = new ArrayList<>();
        itemsResults.add(new ItemsResult("Mì trường thọ", "53 like", "Thanh Nhàn", "30 phút", "Ăn chính", "Trung bình", R.drawable.ic_favorite_red, "9 công thức", "https://images.pexels.com/photos/3026808/pexels-photo-3026808.jpeg?cs=srgb&amp;dl=asian-food-bowl-food-photography-3026808.jpg&amp;fm=jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-9/61090498_1285841494925963_1183091008456359936_n.jpg?_nc_cat=101&_nc_oc=AQku48FA2VJMthy4eielu0KedlmEBsZv4FU9fcdsRZJtcGwPZ3dUVSTgrRVO0X4OhCw&_nc_ht=scontent.fsgn10-1.fna&oh=a5d303ff39ed42210e11999740d8be57&oe=5DF0D403"));
        itemsResults.add(new ItemsResult("Lẩu dê", "25 like", "Bá Khánh", "50 phút", "Ăn chính", "Trung bình", R.drawable.ic_favorite_border_black, "3 công thức", "https://cdn.daynauan.info.vn/wp-content/uploads/2018/12/lau-de-ham-thuoc-bac.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-1/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlb5oGWp-hplgb-lY9fSd9yyxKfdaF9sBELeGp2pWzeBOz9TBk5Pa2Hq4qSYy2i054&_nc_ht=scontent.fsgn10-1.fna&oh=d20e20e77ceffdfdd97896217f94dd1c&oe=5E3A16D7"));
        itemsResults.add(new ItemsResult("Thịt ba chỉ kho tiêu", "5 like", "Bá Khánh", "35 phút", "Ăn chính", "Trung bình", R.drawable.ic_favorite_red, "3 công thức", "https://monngonchuabenh.com/wp-content/uploads/2016/06/thit-ba-roi-kho-tieu-thom-ngon-1024x576.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-1/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlb5oGWp-hplgb-lY9fSd9yyxKfdaF9sBELeGp2pWzeBOz9TBk5Pa2Hq4qSYy2i054&_nc_ht=scontent.fsgn10-1.fna&oh=d20e20e77ceffdfdd97896217f94dd1c&oe=5E3A16D7"));
        itemsResults.add(new ItemsResult("Tôm cuốn khoai tây", "9 like", "Minh Thành", "45 phút", "Ăn chính", "Khó", R.drawable.ic_favorite_red, "6 công thức", "http://www.agrexsaigon.com.vn/images/news/129/385/tom-cuon-khoai-tay1.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-9/42702447_309972646225392_437175461110349824_n.jpg?_nc_cat=107&_nc_oc=AQnA-0M1W6GKpUejN3uQGUc8WEouDVZ-uz0HUsR84pWBfFGrwD6AAz-NmevnQjYckpI&_nc_ht=scontent.fsgn10-1.fna&oh=15b8fb2304ba389770ab8d5ea8d4a030&oe=5E34D96E"));
        itemsResults.add(new ItemsResult("Bún mọc sườn heo", "100 like", "Bá Khánh", "50 phút", "Ăn chính", "Trung bình", R.drawable.ic_favorite_border_black, "3 công thức", "https://agiadinh.net/wp-content/uploads/2017/11/cach-nau-bun-moc-4-600x401.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-1/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlb5oGWp-hplgb-lY9fSd9yyxKfdaF9sBELeGp2pWzeBOz9TBk5Pa2Hq4qSYy2i054&_nc_ht=scontent.fsgn10-1.fna&oh=d20e20e77ceffdfdd97896217f94dd1c&oe=5E3A16D7"));
        itemsResults.add(new ItemsResult("Gà hầm thuốc bắc", "250 like", "Bá Khánh", "40 phút", "Ăn chính", "Trung bình", R.drawable.ic_favorite_border_black, "3 công thức", "https://toinayangi.vn/wp-content/uploads/2015/08/g%C3%A0-h%E1%BA%A7m-thu%E1%BB%91c-b%E1%BA%AFc.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-1/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlb5oGWp-hplgb-lY9fSd9yyxKfdaF9sBELeGp2pWzeBOz9TBk5Pa2Hq4qSYy2i054&_nc_ht=scontent.fsgn10-1.fna&oh=d20e20e77ceffdfdd97896217f94dd1c&oe=5E3A16D7"));

        updateRecycler();
    }
    private void initView() {
        recyclerView = findViewById(R.id.rcv_view_history);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewHistoryActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void updateRecycler() {
        if (searchAdapter == null) {
            searchAdapter = new SearchAdapter(itemsResults, ViewHistoryActivity.this);
            recyclerView.setAdapter(searchAdapter);
            searchAdapter.setmOnTopDishy(new SearchAdapter.OnTopDishy() {
                @Override
                public void onClick(Dishy dishy) {
                    Intent intent = new Intent(ViewHistoryActivity.this, RecipeActivity.class);
                    intent.putExtra("DISHY", dishy);
                    startActivity(intent);
                }
            });
        } else {
            searchAdapter.notifyDataSetChanged();
        }

    }
}
