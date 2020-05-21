package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.DataNotification;
import com.example.dishycloud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    List<DataNotification> dataNotifications;
    Context context;

    public NotificationAdapter(List<DataNotification> dataNotifications, Context context) {
        this.dataNotifications = dataNotifications;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtContent.setText(dataNotifications.get(position).getContent());
        holder.txtTime.setText(dataNotifications.get(position).getTime());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.build().load(dataNotifications.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img);
        builder.build().load(dataNotifications.get(position).getAvatar())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.avatar);
        holder.loginName.setText(dataNotifications.get(position).getLoginName());
        holder.likeNumber.setText(dataNotifications.get(position).getLikeNumber());
    }

    @Override
    public int getItemCount() {
        return dataNotifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtContent;
        ImageView img;
        TextView txtTime;
        ImageView avatar;
        TextView likeNumber;
        TextView loginName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtContent = (TextView)itemView.findViewById(R.id.txtContent);
            img = (ImageView)itemView.findViewById(R.id.imgHinh_item_row);
            txtTime = (TextView)itemView.findViewById(R.id.txtTime_item_row);
            avatar = itemView.findViewById(R.id.avatar_item_row);
            likeNumber = itemView.findViewById(R.id.likeNumber_item_row);
            loginName = itemView.findViewById(R.id.loginName_item_row);
        }
    }
}
