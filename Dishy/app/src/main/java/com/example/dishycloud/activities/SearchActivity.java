package com.example.dishycloud.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.dishycloud.R;
import com.example.dishycloud.adaptes.QuickSearchAdapter;
import com.example.dishycloud.adaptes.SearchAdapter;
import com.example.dishycloud.bottomSheets.BottomSheetChooseOption;
import com.example.dishycloud.bottomSheets.CallBackOption;
import com.example.dishycloud.models.ChooseOptionBottomSheet;
import com.example.dishycloud.models.Dishy;
import com.example.dishycloud.models.ItemsQuickSearch;
import com.example.dishycloud.models.ItemsResult;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private List<ItemsResult> itemsResults;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;

    private List<ItemsQuickSearch> quickSearchList;
    private RecyclerView recyclerViewQuick;
    private QuickSearchAdapter quickSearchAdapter;

    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layoutBtnSort;
    private LinearLayout layoutBtnFillter;
    private LinearLayout layoutBtnViewHistory;

    private TextView txtLastSearch1;
    private TextView txtLastSearch2;
    private EditText edtSearch;
    private ImageView mImgBack;

    private List<ChooseOptionBottomSheet> optionBottomSheetsFilter;
    private List<ChooseOptionBottomSheet> optionBottomSheetsSort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
    }


    private void initData() {
        mImgBack.setOnClickListener(this);
        txtLastSearch1.setOnClickListener(this);
        txtLastSearch2.setOnClickListener(this);
        recyclerViewQuick.setOnClickListener(this);
        layoutBtnSort.setOnClickListener(this);
        layoutBtnFillter.setOnClickListener(this);
        layoutBtnViewHistory.setOnClickListener(this);
        recyclerView.setOnClickListener(this);
        edtSearch.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        itemsResults = new ArrayList<>();
        itemsResults.add(new ItemsResult("Mì trường thọ", "53", "Thanh Nhàn", "30 phút", "Ăn chính", "Trung bình", R.drawable.ic_favorite_red, "9 công thức", "https://images.pexels.com/photos/3026808/pexels-photo-3026808.jpeg?cs=srgb&amp;dl=asian-food-bowl-food-photography-3026808.jpg&amp;fm=jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-9/61090498_1285841494925963_1183091008456359936_n.jpg?_nc_cat=101&_nc_oc=AQku48FA2VJMthy4eielu0KedlmEBsZv4FU9fcdsRZJtcGwPZ3dUVSTgrRVO0X4OhCw&_nc_ht=scontent.fsgn10-1.fna&oh=a5d303ff39ed42210e11999740d8be57&oe=5DF0D403"));
        itemsResults.add(new ItemsResult("Cơm cuộn hàn quốc", "10", "Thanh Nhàn", "30 phút", "Ăn chính", "Dễ", R.drawable.ic_favorite_red, "9 công thức", "https://cdn.daynauan.info.vn/wp-content/uploads/2018/07/lau-ga-la-giang.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-9/61090498_1285841494925963_1183091008456359936_n.jpg?_nc_cat=101&_nc_oc=AQku48FA2VJMthy4eielu0KedlmEBsZv4FU9fcdsRZJtcGwPZ3dUVSTgrRVO0X4OhCw&_nc_ht=scontent.fsgn10-1.fna&oh=a5d303ff39ed42210e11999740d8be57&oe=5DF0D403"));
        itemsResults.add(new ItemsResult("Cánh gà chiên nước mắm", "13", "Minh Thành", "40 phút", "Ăn chính", "Dễ", R.drawable.ic_favorite_border_black, "6 công thức", "https://znews-photo.zadn.vn/w660/Uploaded/Ohunoaa/2016_12_31/d6.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-9/42702447_309972646225392_437175461110349824_n.jpg?_nc_cat=107&_nc_oc=AQnA-0M1W6GKpUejN3uQGUc8WEouDVZ-uz0HUsR84pWBfFGrwD6AAz-NmevnQjYckpI&_nc_ht=scontent.fsgn10-1.fna&oh=15b8fb2304ba389770ab8d5ea8d4a030&oe=5E34D96E"));
        itemsResults.add(new ItemsResult("Cơm tấm", "30", "Minh Thành", "40 phút", "Ăn sáng", "Dễ", R.drawable.ic_favorite_border_black, "6 công thức", "https://baogiadinhso.com/wp-content/uploads/2018/12/cach-nau-com-tam-suon-bi-cha.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-9/42702447_309972646225392_437175461110349824_n.jpg?_nc_cat=107&_nc_oc=AQnA-0M1W6GKpUejN3uQGUc8WEouDVZ-uz0HUsR84pWBfFGrwD6AAz-NmevnQjYckpI&_nc_ht=scontent.fsgn10-1.fna&oh=15b8fb2304ba389770ab8d5ea8d4a030&oe=5E34D96E"));
        itemsResults.add(new ItemsResult("Lẩu dê", "25", "Bá Khánh", "50 phút", "Ăn chính", "Trung bình", R.drawable.ic_favorite_border_black, "3 công thức", "https://cdn.daynauan.info.vn/wp-content/uploads/2018/12/lau-de-ham-thuoc-bac.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-1/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlb5oGWp-hplgb-lY9fSd9yyxKfdaF9sBELeGp2pWzeBOz9TBk5Pa2Hq4qSYy2i054&_nc_ht=scontent.fsgn10-1.fna&oh=d20e20e77ceffdfdd97896217f94dd1c&oe=5E3A16D7"));
        itemsResults.add(new ItemsResult("Thịt ba chỉ kho tiêu", "5", "Bá Khánh", "35 phút", "Ăn chính", "Trung bình", R.drawable.ic_favorite_red, "3 công thức", "https://monngonchuabenh.com/wp-content/uploads/2016/06/thit-ba-roi-kho-tieu-thom-ngon-1024x576.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-1/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlb5oGWp-hplgb-lY9fSd9yyxKfdaF9sBELeGp2pWzeBOz9TBk5Pa2Hq4qSYy2i054&_nc_ht=scontent.fsgn10-1.fna&oh=d20e20e77ceffdfdd97896217f94dd1c&oe=5E3A16D7"));
        itemsResults.add(new ItemsResult("Tôm cuốn khoai tây", "9", "Minh Thành", "45 phút", "Ăn chính", "Khó", R.drawable.ic_favorite_red, "6 công thức", "https://images.pexels.com/photos/1199960/pexels-photo-1199960.jpeg?cs=srgb&amp;dl=burger-cheeseburger-close-up-1199960.jpg&amp;fm=jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-9/42702447_309972646225392_437175461110349824_n.jpg?_nc_cat=107&_nc_oc=AQnA-0M1W6GKpUejN3uQGUc8WEouDVZ-uz0HUsR84pWBfFGrwD6AAz-NmevnQjYckpI&_nc_ht=scontent.fsgn10-1.fna&oh=15b8fb2304ba389770ab8d5ea8d4a030&oe=5E34D96E"));
        itemsResults.add(new ItemsResult("Bún mọc sườn heo", "100", "Bá Khánh", "50 phút", "Ăn chính", "Trung bình", R.drawable.ic_favorite_border_black, "3 công thức", "https://agiadinh.net/wp-content/uploads/2017/11/cach-nau-bun-moc-4-600x401.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-1/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlb5oGWp-hplgb-lY9fSd9yyxKfdaF9sBELeGp2pWzeBOz9TBk5Pa2Hq4qSYy2i054&_nc_ht=scontent.fsgn10-1.fna&oh=d20e20e77ceffdfdd97896217f94dd1c&oe=5E3A16D7"));
        itemsResults.add(new ItemsResult("Gà hầm thuốc bắc", "250", "Bá Khánh", "40 phút", "Ăn chính", "Trung bình", R.drawable.ic_favorite_border_black, "3 công thức", "https://toinayangi.vn/wp-content/uploads/2015/08/g%C3%A0-h%E1%BA%A7m-thu%E1%BB%91c-b%E1%BA%AFc.jpg", "https://scontent.fsgn10-1.fna.fbcdn.net/v/t1.0-1/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlb5oGWp-hplgb-lY9fSd9yyxKfdaF9sBELeGp2pWzeBOz9TBk5Pa2Hq4qSYy2i054&_nc_ht=scontent.fsgn10-1.fna&oh=d20e20e77ceffdfdd97896217f94dd1c&oe=5E3A16D7"));

        quickSearchList = new ArrayList<>();
        quickSearchList.add(new ItemsQuickSearch("Ăn sáng", "https://png.pngtree.com/png-vector/20190130/ourlarge/pngtree-food-elements-hand-drawn-cute-cartoon-breakfast-dessert-omelette-elementhand-drawn-png-image_613496.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Món chính", "https://png.pngtree.com/png-clipart/20190115/ourlarge/pngtree-winter-food-free-food-special-food-creative-food-png-image_359446.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Ăn vặt", "https://png.pngtree.com/png-clipart/20190117/ourlarge/pngtree-delicious-snacks-beautiful-snack-hand-painted-snacks-cartoon-snack-png-image_412624.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Bánh", "https://png.pngtree.com/png-vector/20190130/ourlarge/pngtree-food-elements-hand-drawn-cute-cartoon-gourmet-donuts-elementhand-drawn-foodcartoon-png-image_614532.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Ăn chay", "https://png.pngtree.com/png-vector/20190116/ourlarge/pngtree-vegetable-salad-food-vegetables-vegetable-salad-food-pattern-png-image_388734.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Healthy", "https://png.pngtree.com/png-vector/20190130/ourlarge/pngtree-mbe-style-cartoon-cute-vegetable-carrot-vegetablescute-vegetablesmbe-vegetablescarrot-png-image_590422.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Thức uống", "https://png.pngtree.com/png-clipart/20190115/ourlarge/pngtree-food-coffee-coffee-frappuccino-png-image_369400.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Snacks", "https://png.pngtree.com/png-clipart/20190117/ourlarge/pngtree-delicious-popcorn-nutritional-popcorn-snack-popcorn-hand-painted-popcorn-png-image_415111.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Lẩu", "https://png.pngtree.com/element_our/png_detail/20181127/404-lang-png_248262.jpg"));
        quickSearchList.add(new ItemsQuickSearch("Salad", "https://png.pngtree.com/element_origin_min_pic/16/08/03/1457a194373fea7.jpg"));


        updateRecycler();

        optionBottomSheetsSort = new ArrayList<>();
        optionBottomSheetsSort.add(new ChooseOptionBottomSheet(1, "Thời gian"));
        optionBottomSheetsSort.add(new ChooseOptionBottomSheet(2, "Lượt xem"));
        optionBottomSheetsSort.add(new ChooseOptionBottomSheet(3, "Yêu thích"));

        optionBottomSheetsFilter = new ArrayList<>();
        optionBottomSheetsFilter.add(new ChooseOptionBottomSheet(1, "Dễ"));
        optionBottomSheetsFilter.add(new ChooseOptionBottomSheet(2, "Trung bình"));
        optionBottomSheetsFilter.add(new ChooseOptionBottomSheet(3, "Khó"));

    }

    private void initView() {
        mImgBack = findViewById(R.id.img_back_search);
        txtLastSearch1 = findViewById(R.id.txtLastSearch1);
        txtLastSearch2 = findViewById(R.id.txtLastSearch2);


        recyclerView = findViewById(R.id.recyclerResult);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        layout1 = findViewById(R.id.resultLayout);

        recyclerViewQuick = findViewById(R.id.rvQuickSearch);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerViewQuick.setLayoutManager(layoutManager);
        layout2 = findViewById(R.id.suggestionsLayout);

        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);

        edtSearch = findViewById(R.id.edt_search_sa);
        layoutBtnSort = findViewById(R.id.ll_buttom_sort_sheet);
        layoutBtnFillter = findViewById(R.id.ll_buttom_fillter);
        layoutBtnViewHistory = findViewById(R.id.ll_buttom_view_history);
    }

    private void showResult() {
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);

    }

    private void bottomSheetSortUnit(){
        BottomSheetChooseOption bottomSheetChooseOption = new BottomSheetChooseOption(this, "Sắp xếp", optionBottomSheetsSort);

        bottomSheetChooseOption.show(getSupportFragmentManager(), "unitMaterial");
        bottomSheetChooseOption.getChooseString(new CallBackOption() {
            @Override
            public void chooseOption(ChooseOptionBottomSheet method, int position) {

            }
        });

    }

    private void bottomSheetFillterUnit(){
        BottomSheetChooseOption bottomSheetChooseOption = new BottomSheetChooseOption(this, "Độ khó", optionBottomSheetsFilter);

        bottomSheetChooseOption.show(getSupportFragmentManager(), "unitMaterial");
        bottomSheetChooseOption.getChooseString(new CallBackOption() {
            @Override
            public void chooseOption(ChooseOptionBottomSheet method, int position) {

            }
        });

    }

    private void updateRecycler() {
        if (searchAdapter == null) {
            searchAdapter = new SearchAdapter(itemsResults, SearchActivity.this);
            recyclerView.setAdapter(searchAdapter);
            searchAdapter.setmOnTopDishy(new SearchAdapter.OnTopDishy() {
                @Override
                public void onClick(Dishy dishy) {
                    Intent intent = new Intent(SearchActivity.this, RecipeActivity.class);
                    intent.putExtra("DISHY", dishy);
                    startActivity(intent);
                }
            });
        } else {
            searchAdapter.notifyDataSetChanged();
        }

        if (quickSearchAdapter == null) {
            quickSearchAdapter = new QuickSearchAdapter(quickSearchList, SearchActivity.this);
            recyclerViewQuick.setAdapter(quickSearchAdapter);
        } else {
            quickSearchAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rvQuickSearch:
                showResult();
                break;
            case R.id.txtLastSearch1:
                showResult();
                break;
            case R.id.txtLastSearch2:
                showResult();
                break;
            case R.id.ll_buttom_sort_sheet:
                bottomSheetSortUnit();
                break;
            case R.id.ll_buttom_fillter:
                bottomSheetFillterUnit();
                break;
            case R.id.ll_buttom_view_history:
                Intent intent = new Intent(this, ViewHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.img_back_search:finish();break;
        }
    }
}
