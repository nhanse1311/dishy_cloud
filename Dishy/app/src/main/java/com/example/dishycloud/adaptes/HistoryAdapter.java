package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.models.DataHistory;
import com.example.dishycloud.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    Context context;
    List<Recipe> dataHistoryList;


    public HistoryAdapter(Context context, List<Recipe> dataHistoryList) {
        this.context = context;
        this.dataHistoryList = dataHistoryList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView txtNameFood;
        TextView txtLikeNumber;
        TextView txtTimeNumber;
        TextView txtLevel;
        TextView txtStatus;
        TextView txtTime;
        ImageView like;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameFood = (TextView)itemView.findViewById(R.id.nameFood_his);
            image = (ImageView)itemView.findViewById(R.id.imageFood_his);
            txtLikeNumber = (TextView)itemView.findViewById(R.id.timeNumber_his);
            txtTimeNumber = (TextView)itemView.findViewById(R.id.timeNumber_his);
            txtLevel = (TextView)itemView.findViewById(R.id.level_his);
            txtStatus = (TextView)itemView.findViewById(R.id.status_his);
            txtTime = (TextView)itemView.findViewById(R.id.txtTime_his);

            like = itemView.findViewById(R.id.like_his);
        }
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_history, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
//        holder.avatar.setImageResource(dataHistoryList.get(position).getAvatar());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.build().load(dataHistoryList.get(position).getImage())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return dataHistoryList.size();
    }
}
