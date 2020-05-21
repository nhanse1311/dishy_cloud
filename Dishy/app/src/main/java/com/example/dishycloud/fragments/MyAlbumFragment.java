package com.example.dishycloud.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.activities.MyRecipeActivity;
import com.example.dishycloud.adaptes.AlbumAdapter;
import com.example.dishycloud.models.Dishy;
import com.example.dishycloud.models.Material;
import com.example.dishycloud.models.StepMake;

import java.util.ArrayList;
import java.util.List;

public class MyAlbumFragment extends Fragment {

    private View mView;
    private RecyclerView rcv_my_ablum;
    private List<Dishy> dishies;
    private List<Material> mMaterial1;
    private List<StepMake> mMakes;
    private AlbumAdapter albumAdapter;

    public static MyAlbumFragment newInstance() {
        MyAlbumFragment myAlbumFragment = new MyAlbumFragment();
        Bundle args = new Bundle();
        myAlbumFragment.setArguments(args);
        return myAlbumFragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my_album, container, false);
        initView();
        initData();
        return mView;
    }

    private void initView() {
        rcv_my_ablum = mView.findViewById(R.id.rcv_my_album);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rcv_my_ablum.setLayoutManager(gridLayoutManager);
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

        mMakes = new ArrayList<>();

        mMakes.add(new StepMake("Đầu tiên ta tiến hành sơ chế mì. Cho vào nồi khoảng 3l nước đun sôi, để cho nước sôi bốc hơi lên thì ta cho mì vào để luộc, ta bỏ trực tiếp mì vào luộc tầm 4- 5 phút sao cho cọng mì mềm thì vớt ra, Cho vào rổ và để ráo nước. Lưu ý về cách luộc mì, tùy vào mỗi loại mì khác nhau mà ta có thời gian luộc sao cho phù hợp, luộc cho đến khi cọng mì vừa mềm, đừng quá dai cũng đừng quá bở. Sau đó để sang một bên"
                , true, "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_b6bab32605fbd5c5495f10d3f33c997e.jpg",
                "https://media.cooky.vn/recipe/g3/26588/s300x300/recipe26588-prepare-step1-636509659345302890.jpg"));
        mMakes.add(new StepMake("Nấm sau khi mua về ta rửa sạch, cho tí muối vào rửa sau đó cắt lát nhỏ và cho vào rổ để ráo. Hẹ cũng thế, ta cũng rửa sạch và cắt từng khúc rồi để ráo. Tiếp đó ta bắt chảo lên bếp, cho vào chảo một tí dầu chờ tới khi chảo nóng dầu thì cho nấm và đầu hẹ vào để xào. Ta xào đều tay cho nấm chín"
                , false, "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8c98b3d8b943222382ee7d6f1d169466.jpg",
                "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8f62c5f0530e72623b28fd84f2ed0c49.jpg"));
        mMakes.add(new StepMake("Sau khi nghe mùi thơm dậy lên của nấm cùng với hẹ, ta tiếp tục cho mì đã luộc để ráo nước vào xào chung cho đến khi mì mềm và nhìn hấp dẫn."
                , false, "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8f62c5f0530e72623b28fd84f2ed0c49.jpg",
                "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8f62c5f0530e72623b28fd84f2ed0c49.jpg"));
        mMakes.add(new StepMake("Sau khi đang xào thì ta cho gia vị vào, cho hạt nêm, dầu có trong mì tôm cùng với dầu me, xì dầu và tương đen, mì xào ta trở đều tay cho gia vị được nêm nếm tan đều và thấm vào cọng mì. Đến khi mì bốc mùi thơm, canh xào khoảng 5 phút thì mì thấm vị và ngon, không bị cháy."
                , false, "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8f62c5f0530e72623b28fd84f2ed0c49.jpg",
                "https://trungquocsensetravel.com/view/at_huong-dan-cach-lam-mon-mi-truong-tho-trung-quoc-ngon-chuan-vi_8f62c5f0530e72623b28fd84f2ed0c49.jpg"));

        dishies = new ArrayList<>();
        dishies.add(new Dishy("Mì Trường Thọ", "https://images.pexels.com/photos/3026808/pexels-photo-3026808.jpeg?cs=srgb&amp;dl=asian-food-bowl-food-photography-3026808.jpg&amp;fm=jpg", "20 phút", 3, 5, 3, "Trung bình", mMakes, mMaterial1));
        dishies.add(new Dishy("Gà sốt phô mai", "https://znews-photo.zadn.vn/w660/Uploaded/Ohunoaa/2016_12_31/d6.jpg", "10 phút", 4, 50,5));
        dishies.add(new Dishy("Lẩu thái", "https://bepmenau.com/wp-content/uploads/2018/05/Lau-Thai-hai-san_8_1.1.359_1124X1685.jpeg", "34 phút", 5, 50,4));
        dishies.add(new Dishy("Kimbap chiên xù", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRqGelogsMrJv1R3tkdQXER63ewilYAUzG4UAO0KWIfSZpGWSn", "12 phút", 2, 30,4));
        dishies.add(new Dishy("Bánh chocolate", "https://images.pexels.com/photos/3026810/pexels-photo-3026810.jpeg?cs=srgb&amp;dl=avocado-chocolate-dessert-3026810.jpg&amp;fm=jpg", "26 phút", 4, 54,4));
        dishies.add(new Dishy("Tôm ghim chua ngọt", "https://images.pexels.com/photos/699544/pexels-photo-699544.jpeg?cs=srgb&amp;dl=chopsticks-cuisine-delicious-699544.jpg&amp;fm=jpg", "20 phút", 4, 30,4));
        dishies.add(new Dishy("Bún đậu mắm tôm", "https://vnn-imgs-f.vgcloud.vn/2018/09/18/12/cach-lam-bun-dau-mam-tom-ngon-nhu-cua-ba-noi-phim-gao-nep-gao-te.jpg", "15 phút", 3, 34,3));

        updateRcvDishy(dishies);
    }

    private void updateRcvDishy(List<Dishy> dishies) {
        if (albumAdapter == null) {
            albumAdapter = new AlbumAdapter(getContext(), dishies, true);
            rcv_my_ablum.setAdapter(albumAdapter);
            albumAdapter.setmOnAlbumClickListener(new AlbumAdapter.OnAlbumClickListener() {
                @Override
                public void OnClick(Dishy dishy) {
                    Intent move = new Intent(getContext(), MyRecipeActivity.class);
                    move.putExtra("MYDISHY",dishy);
                    getActivity().startActivityForResult(move,2019);
                }
            });
        } else {
            albumAdapter.notifyDataSetChanged();
        }
    }

}
