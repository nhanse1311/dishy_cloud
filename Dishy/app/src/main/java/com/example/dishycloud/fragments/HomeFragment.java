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
import android.widget.LinearLayout;
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
import com.example.dishycloud.models.StepMake;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView mRcvDishyToday, mRcvTopDishy, mRcvTopChef, mRcvTopDishyFollow;
    private DishyTodayAdapter mDishyTodayAdapter;
    private TopDishyAdapter mTopDishyAdapter;
    private TopChefAdapter mTopChefAdapter;
    private TopFollowAdapter mTopFollowAdapter;
    private List<Dishy> mDishyList, mTopDishy, mDishyFollowList;
    private List<Chef> mChefs;
    private List<Material> mMaterial1, mMaterial2, mMaterial3, mMaterial4, mMaterial5, mMaterial6, mMaterial7;
    private List<StepMake> mStep1, mStep2, mStep3, mStep4, mStep5, mStep6, mStep7;
    private Chef mChef1;
    private View mView;

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
        mMaterial1 = new ArrayList<>();
        mMaterial1.add(new Material("Thịt vịt", 1, "con"));
        mMaterial1.add(new Material("Thịt xá xíu", 500, "gram"));
        mMaterial1.add(new Material("Tôm sú", 500, "gram"));
        mMaterial1.add(new Material("Mì vàn sợi lớn", 200, "gram"));
        mMaterial1.add(new Material("Bông hẹ", 100, "Gram"));
        mMaterial1.add(new Material("Rau cần tây", 100, "Gram"));
        mMaterial1.add(new Material("Cà rốt", 1, "củ"));
        mMaterial1.add(new Material("hành tây", 1, "củ"));
        mMaterial1.add(new Material("Nấm đông cô", 200, "gram"));
        mMaterial1.add(new Material("Ớt băm nhuyễn", 1, "muỗng"));
        mMaterial1.add(new Material("Nước tương", 2, "muỗng"));
        mMaterial1.add(new Material("Muối", 0.5, "muỗng"));
        mMaterial1.add(new Material("Đường", 1, "muỗng"));
        mMaterial1.add(new Material("Tiêu", 0.5, "muỗng"));
        mMaterial1.add(new Material("Dầu hào", 1, "muỗng"));
        mStep1 = new ArrayList<>();

        mStep1.add(new StepMake("Đầu tiên ta tiến hành sơ chế mì. Cho vào nồi khoảng 3l nước đun sôi, để cho nước sôi bốc hơi lên thì ta cho mì vào để luộc, ta bỏ trực tiếp mì vào luộc tầm 4- 5 phút sao cho cọng mì mềm thì vớt ra, Cho vào rổ và để ráo nước. Lưu ý về cách luộc mì, tùy vào mỗi loại mì khác nhau mà ta có thời gian luộc sao cho phù hợp, luộc cho đến khi cọng mì vừa mềm, đừng quá dai cũng đừng quá bở. Sau đó để sang một bên"
                , true, "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_b6bab32605fbd5c5495f10d3f33c997e.jpg",
                "https://media.cooky.vn/recipe/g3/26588/s300x300/recipe26588-prepare-step1-636509659345302890.jpg"));
        mStep1.add(new StepMake("Nấm sau khi mua về ta rửa sạch, cho tí muối vào rửa sau đó cắt lát nhỏ và cho vào rổ để ráo. Hẹ cũng thế, ta cũng rửa sạch và cắt từng khúc rồi để ráo. Tiếp đó ta bắt chảo lên bếp, cho vào chảo một tí dầu chờ tới khi chảo nóng dầu thì cho nấm và đầu hẹ vào để xào. Ta xào đều tay cho nấm chín"
                , false, "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8c98b3d8b943222382ee7d6f1d169466.jpg",
                "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8f62c5f0530e72623b28fd84f2ed0c49.jpg"));
        mStep1.add(new StepMake("Sau khi nghe mùi thơm dậy lên của nấm cùng với hẹ, ta tiếp tục cho mì đã luộc để ráo nước vào xào chung cho đến khi mì mềm và nhìn hấp dẫn."
                , false, "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8f62c5f0530e72623b28fd84f2ed0c49.jpg",
                "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8f62c5f0530e72623b28fd84f2ed0c49.jpg"));
        mStep1.add(new StepMake("Sau khi đang xào thì ta cho gia vị vào, cho hạt nêm, dầu có trong mì tôm cùng với dầu me, xì dầu và tương đen, mì xào ta trở đều tay cho gia vị được nêm nếm tan đều và thấm vào cọng mì. Đến khi mì bốc mùi thơm, canh xào khoảng 5 phút thì mì thấm vị và ngon, không bị cháy."
                , false, "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8f62c5f0530e72623b28fd84f2ed0c49.jpg",
                "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8f62c5f0530e72623b28fd84f2ed0c49.jpg"));
        mChef1 = new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-1/p320x320/61090498_1285841494925963_1183091008456359936_n.jpg?_nc_cat=101&_nc_oc=AQkXDsimHahDSzxF7BS9NbBvgox8P-BAyPNh2DvJlOZdkZqhBm3KS206w5f7cw1PBneiFi6EtydeF5Gf1avxxUxS&_nc_ht=scontent.fsgn5-1.fna&oh=5092122e39724a586043169cf47d0696&oe=5E2FC72F", "Nguyễn Thanh Nhàn", 100, 1000, mDishyList);

        mDishyList = new ArrayList<>();
        mDishyList.add(new Dishy("Mì Trường Thọ", "https://images.pexels.com/photos/3026808/pexels-photo-3026808.jpeg?cs=srgb&amp;dl=asian-food-bowl-food-photography-3026808.jpg&amp;fm=jpg", "20 phút", 3, 5, 3, "Trung bình", mStep1, mMaterial1));
        mDishyList.add(new Dishy("Gà sốt phô mai", "https://znews-photo.zadn.vn/w660/Uploaded/Ohunoaa/2016_12_31/d6.jpg", "10 phút", 4, 50,3));
        mDishyList.add(new Dishy("Lẩu thái", "https://bepmenau.com/wp-content/uploads/2018/05/Lau-Thai-hai-san_8_1.1.359_1124X1685.jpeg", "34 phút", 5, 50,4));
        mDishyList.add(new Dishy("Kimbap chiên xù", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRqGelogsMrJv1R3tkdQXER63ewilYAUzG4UAO0KWIfSZpGWSn", "12 phút", 2, 30,4));
        mDishyList.add(new Dishy("Bánh chocolate", "https://images.pexels.com/photos/3026810/pexels-photo-3026810.jpeg?cs=srgb&amp;dl=avocado-chocolate-dessert-3026810.jpg&amp;fm=jpg", "26 phút", 4, 54,3));
        mDishyList.add(new Dishy("Tôm ghim chua ngọt", "https://images.pexels.com/photos/699544/pexels-photo-699544.jpeg?cs=srgb&amp;dl=chopsticks-cuisine-delicious-699544.jpg&amp;fm=jpg", "20 phút", 4, 30,5));
        mDishyList.add(new Dishy("Bún đậu mắm tôm", "https://vnn-imgs-f.vgcloud.vn/2018/09/18/12/cach-lam-bun-dau-mam-tom-ngon-nhu-cua-ba-noi-phim-gao-nep-gao-te.jpg", "15 phút", 3, 34,5));
        updateUIRcvDishyToDay(mDishyList);

        mTopDishy = new ArrayList<>();
        mTopDishy.add(new Dishy("Mì Trường Thọ", "https://images.pexels.com/photos/3026808/pexels-photo-3026808.jpeg?cs=srgb&amp;dl=asian-food-bowl-food-photography-3026808.jpg&amp;fm=jpg", "20 phút", 3, 5,  "Trung bình",53, mStep1, mMaterial1,mChef1));
        mTopDishy.add(new Dishy("Bánh tráng trộn", "https://i.ytimg.com/vi/8lNLepEuR8I/maxresdefault.jpg", "24 phút", 5, "Khó", 100));
        mTopDishy.add(new Dishy("Lẩu gà lá giang", "https://cdn.daynauan.info.vn/wp-content/uploads/2018/07/lau-ga-la-giang.jpg", "20 phút", 3, "Dễ", 200));
        mTopDishy.add(new Dishy("Mì cay", "https://cdn.tgdd.vn/Files/2016/07/26/863246/cach-lam-mi-cay-3-cap-do-cho-ngay-mua-them-am-bung4.jpg", "18 phút", 2, "Trung bình", 230));
        mTopDishy.add(new Dishy("Hamburger thịt bò", "https://images.pexels.com/photos/1199960/pexels-photo-1199960.jpeg?cs=srgb&amp;dl=burger-cheeseburger-close-up-1199960.jpg&amp;fm=jpg", "Dễ", 4, "Dễ", 500));
        updateUIRcvTopDishy(mTopDishy);

        mChefs =  new ArrayList<>();
        mChefs.add(new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-1/p320x320/61090498_1285841494925963_1183091008456359936_n.jpg?_nc_cat=101&_nc_oc=AQkXDsimHahDSzxF7BS9NbBvgox8P-BAyPNh2DvJlOZdkZqhBm3KS206w5f7cw1PBneiFi6EtydeF5Gf1avxxUxS&_nc_ht=scontent.fsgn5-1.fna&oh=5092122e39724a586043169cf47d0696&oe=5E2FC72F","Nguyễn Thanh Nhàn",100,1000,mDishyList));
        mChefs.add(new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/70172923_530781041027944_2863099953419386880_o.jpg?_nc_cat=101&_nc_oc=AQlFYB2tkZCgQUyLzRy8IO8HK8iejP05xdDPdg9v6BQRynJ23HLIN2fRf5kdmsrDikZee4UamPU5-Kx540iCc_Nh&_nc_ht=scontent.fsgn5-1.fna&oh=fbdca8ce8e94d3f0362b0b88a6de1a60&oe=5DF68723","Diệp Thị Hiếu Phụng",100,989,mDishyList));
        mChefs.add(new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/c94.0.320.320a/p320x320/10354686_10150004552801856_220367501106153455_n.jpg?_nc_cat=1&_nc_oc=AQnkg1FO0BOUe_3tyQjvLTX0BQknJmTx2Xi46NAM5vcMHW1iain3p1l3Wtld3oXnWyHbYc7B8ePQLpI3SaicuUX2&_nc_ht=scontent.fsgn5-2.fna&oh=9ec1f59f0c8d4f8cc46af0a329d330da&oe=5DFFDC59","Nguyễn Văn Hoà",90,800,mDishyList));
        mChefs.add(new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/p320x320/42702447_309972646225392_437175461110349824_n.jpg?_nc_cat=107&_nc_oc=AQltiSM9CxF69luR0u8RtBS8zt-vjOsQhNThCzmsDSEskyPJJ0OIs5mDc53b-qc6wEzMIhuB-pKxmRBsfaIUk1SZ&_nc_ht=scontent.fsgn5-2.fna&oh=4e4e4be1e05f5f224306f01f99a9dadb&oe=5E351777","Phạm Minh Thành",80,700,mDishyList));
        mChefs.add(new Chef("https://scontent.fsgn5-6.fna.fbcdn.net/v/t1.0-0/s206x206/67623070_1513383675469863_7139297301738029056_n.jpg?_nc_cat=106&_nc_oc=AQlDpAwagXzUiYMNII1OTEUgF_hbe4JsVVXtGQrh4NdQdiCOyqobyD8Yp0kAqS7q6Kl9ywuetZvDPdqneKAU3pIa&_nc_ht=scontent.fsgn5-6.fna&oh=979a38f27a9022b4abb79921f421f9e4&oe=5E33A071","Nguyễn Phúc Hậu",70,520,mDishyList));
        mChefs.add(new Chef("https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-1/p320x320/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlqW0sH4rpBiS2IPFfCEaorz-_7CDmDvZCV3YdL5u0-dyJcqxhMRJMtySaDEMPjS-uGvtyeUqP4ZJT4328aZLhU&_nc_ht=scontent.fsgn5-3.fna&oh=ea35b802e27df0b8c9f7bc852baec68f&oe=5E023271","Nguyễn Hoàng Bá Khánh",60,450,mDishyList));
        mChefs.add(new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/p320x320/53412001_630154360777669_6753580234643079168_n.jpg?_nc_cat=105&_nc_oc=AQke1iRQnNv-Oa0QHsOIrHt-ZImOFADfaBh2P6WoMF4Np-Cz0vTY6KAE_rkYdWzNoB5kekgk2Jk08fOMI55IYj1v&_nc_ht=scontent.fsgn5-2.fna&oh=43c8e8a45e0d7d2ba4c66235fa403110&oe=5E00C2A0","Nguyễn Nghĩa Mai Linh",50,430,mDishyList));
        mChefs.add(new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-1/p320x320/983876_369442600117243_1445371059219521621_n.jpg?_nc_cat=101&_nc_oc=AQlJTywK2U-4NaFc2ikP6TZY-Q6jQxc3vEog1YcOSfe4E08-YxspR4aCkzNzivL3qbAVczrAaelzFrveGJqtw_m9&_nc_ht=scontent.fsgn5-1.fna&oh=591e9cc67683820744e9363778dfcfb3&oe=5E2CA73B","Nguyễn Bảo Thiện",30,390,mDishyList));
        mChefs.add(new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/c0.0.320.320a/p320x320/10599638_1504520149789789_4487427289442049165_n.jpg?_nc_cat=107&_nc_oc=AQne8gyZOiKbnc5zvKNLPtE9JeuC3flPcvR_za52mOc-3TbE4W7PH4f7z6JNwOznFtH-JPIglXn769P5Kl4ORJ0e&_nc_ht=scontent.fsgn5-2.fna&oh=4a4dbfc66aa3a1fb5aeeefa754ce422f&oe=5E2E96E2","Nguyễn Văn Lương",20,300,mDishyList));
        mChefs.add(new Chef("https://scontent.fsgn5-4.fna.fbcdn.net/v/t1.0-1/p320x320/56922724_993070917567979_1385984228432281600_n.jpg?_nc_cat=104&_nc_oc=AQkclC0oXgatAne3ddan4uDnoLHScDDXh1r4TaQB4lHiZI5efE7WrAqUxsCP2IXJpKAKVPEx_GNkxUvsJI1k4M2u&_nc_ht=scontent.fsgn5-4.fna&oh=1811529e821ba45253c10d35236a335b&oe=5DF418A8","Dương Đức Duy ",10,100,mDishyList));
        updateUIRcvTopChef(mChefs);

        Chef chef1 = new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-1/p320x320/61090498_1285841494925963_1183091008456359936_n.jpg?_nc_cat=101&_nc_oc=AQkXDsimHahDSzxF7BS9NbBvgox8P-BAyPNh2DvJlOZdkZqhBm3KS206w5f7cw1PBneiFi6EtydeF5Gf1avxxUxS&_nc_ht=scontent.fsgn5-1.fna&oh=5092122e39724a586043169cf47d0696&oe=5E2FC72F","Nguyễn Thanh Nhàn",100,1000,mDishyList);
        Chef chef2 = new Chef("https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-1/p320x320/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlqW0sH4rpBiS2IPFfCEaorz-_7CDmDvZCV3YdL5u0-dyJcqxhMRJMtySaDEMPjS-uGvtyeUqP4ZJT4328aZLhU&_nc_ht=scontent.fsgn5-3.fna&oh=ea35b802e27df0b8c9f7bc852baec68f&oe=5E023271","Nguyễn Hoàng Bá Khánh",60,450,mDishyList);
        Chef chef3 = new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/c0.0.320.320a/p320x320/10599638_1504520149789789_4487427289442049165_n.jpg?_nc_cat=107&_nc_oc=AQne8gyZOiKbnc5zvKNLPtE9JeuC3flPcvR_za52mOc-3TbE4W7PH4f7z6JNwOznFtH-JPIglXn769P5Kl4ORJ0e&_nc_ht=scontent.fsgn5-2.fna&oh=4a4dbfc66aa3a1fb5aeeefa754ce422f&oe=5E2E96E2","Nguyễn Văn Lương",20,300,mDishyList);
        mDishyFollowList = new ArrayList<>();
        mDishyFollowList.add(new Dishy("Mì cay", "https://cdn.tgdd.vn/Files/2016/07/26/863246/cach-lam-mi-cay-3-cap-do-cho-ngay-mua-them-am-bung4.jpg", "18 phút", 2, "Trung bình", 230, chef1));
        mDishyFollowList.add(new Dishy("Lẩu gà lá giang", "https://cdn.daynauan.info.vn/wp-content/uploads/2018/07/lau-ga-la-giang.jpg", "20 phút", 3, "Dễ", 200, chef1));
        mDishyFollowList.add(new Dishy("Hamburger thịt bò", "https://images.pexels.com/photos/1199960/pexels-photo-1199960.jpeg?cs=srgb&amp;dl=burger-cheeseburger-close-up-1199960.jpg&amp;fm=jpg", "Dễ", 4, "Dễ", 500, chef2));
        mDishyFollowList.add(new Dishy("Bánh tráng trộn", "https://i.ytimg.com/vi/8lNLepEuR8I/maxresdefault.jpg", "24 phút", 5, "Khó", 100, chef3));
        updateUIRcvDishyFollow(mDishyFollowList);


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

    private void updateUIRcvDishyToDay(List<Dishy> dishyList) {
        if (mDishyTodayAdapter == null) {
            mDishyTodayAdapter = new DishyTodayAdapter(getContext(), dishyList);
            mRcvDishyToday.setAdapter(mDishyTodayAdapter);
            mDishyTodayAdapter.setmOnDishyToDayClickListener(new DishyTodayAdapter.OnDishyToDayClickListener() {
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

    private void updateUIRcvTopDishy(List<Dishy> topDishy){
        if (mTopDishyAdapter==null){
            mTopDishyAdapter =  new TopDishyAdapter(getContext(),topDishy);
            mRcvTopDishy.setAdapter(mTopDishyAdapter);
            mTopDishyAdapter.setmOnTopDishy(new TopDishyAdapter.OnTopDishy() {
                @Override
                public void onClick(Dishy dishy) {
                    Intent intent = new Intent(getContext(), RecipeActivity.class);
                    intent.putExtra("DISHY", dishy);
                    startActivity(intent);
                }
            });
        }else{
            mTopDishyAdapter.notifyDataSetChanged();
        }
    }

    private void updateUIRcvTopChef(List<Chef> chefs){
        if (mTopChefAdapter==null){
            mTopChefAdapter =  new TopChefAdapter(getContext(),chefs);
            mRcvTopChef.setAdapter(mTopChefAdapter);
            mTopChefAdapter.setmOnTopChefClickListener(new TopChefAdapter.OnTopChefClickListener() {
                @Override
                public void onClick(Chef chef) {
                    Intent intent = new Intent(getContext(), ChefActivity.class);
                    intent.putExtra("CHEF",chef);
                    startActivity(intent);
                }
            });
        }else{
            mTopChefAdapter.notifyDataSetChanged();
        }
    }
}
