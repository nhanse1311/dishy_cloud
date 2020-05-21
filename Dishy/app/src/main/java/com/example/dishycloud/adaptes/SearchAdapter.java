package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dishycloud.R;
import com.example.dishycloud.models.Chef;
import com.example.dishycloud.models.Dishy;
import com.example.dishycloud.models.ItemsResult;
import com.example.dishycloud.models.Material;
import com.example.dishycloud.models.StepMake;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<StepMake> mStep1;
    List<Material> mMaterial1;
    Chef mChef1;
    Dishy dishy;

    public interface OnTopDishy {
        void onClick(Dishy dishy);
    }

    private List<ItemsResult> items;
    private Context context;
    private OnTopDishy mOnTopDishy;

    public SearchAdapter(List<ItemsResult> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public void setmOnTopDishy(OnTopDishy mOnTopDishy) {
        this.mOnTopDishy = mOnTopDishy;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.result_item,parent,false);
        getItem();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameFood.setText(items.get(position).getFoodName());
        holder.likeNumber.setText(items.get(position).getLikeNumber());
        holder.timeNumber.setText(items.get(position).getTime());
        holder.level.setText(items.get(position).getLevel());
        holder.foodType.setText(items.get(position).getFoodType());
        holder.numRecipe.setText(items.get(position).getNumRecipe());
        holder.like.setImageResource(items.get(position).getLike());
        holder.nameLogin.setText(items.get(position).getAccountName());


        Picasso.Builder builder = new Picasso.Builder(context);
        builder.build().load(items.get(position).getImageFood()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imageFood);
        builder.build().load(items.get(position).getAvatar()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.avatar);

        holder.llFormInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnTopDishy.onClick(dishy);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageFood;
        private TextView nameFood;
        private TextView likeNumber;
        private TextView timeNumber;
        private TextView level;
        private TextView foodType;
        private ImageView avatar;
        private TextView numRecipe;
        private ImageView like;
        private TextView nameLogin;
        private LinearLayout llFormInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFood = itemView.findViewById(R.id.imageFood);
            nameFood = itemView.findViewById(R.id.nameFood);
            likeNumber = itemView.findViewById(R.id.likeNumber);
            timeNumber = itemView.findViewById(R.id.timeNumber);
            level = itemView.findViewById(R.id.level);
            foodType = itemView.findViewById(R.id.foodType);
            avatar = itemView.findViewById(R.id.avatar);
            numRecipe = itemView.findViewById(R.id.numRecipe);
            like = itemView.findViewById(R.id.like);
            nameLogin = itemView.findViewById(R.id.loginName);
            llFormInfo = itemView.findViewById(R.id.item_result_search);
        }
    }

    private void getItem() {

        mStep1 = new ArrayList<>();
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

        mChef1 = new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-1/p320x320/61090498_1285841494925963_1183091008456359936_n.jpg?_nc_cat=101&_nc_oc=AQkXDsimHahDSzxF7BS9NbBvgox8P-BAyPNh2DvJlOZdkZqhBm3KS206w5f7cw1PBneiFi6EtydeF5Gf1avxxUxS&_nc_ht=scontent.fsgn5-1.fna&oh=5092122e39724a586043169cf47d0696&oe=5E2FC72F", "Nguyễn Thanh Nhàn", 100, 1000, null);


        dishy = new Dishy("Mì Trường Thọ", "https://images.pexels.com/photos/3026808/pexels-photo-3026808.jpeg?cs=srgb&amp;dl=asian-food-bowl-food-photography-3026808.jpg&amp;fm=jpg", "20 phút", 3, 5, "Trung bình", 53, mStep1, mMaterial1, mChef1);
    }

}
