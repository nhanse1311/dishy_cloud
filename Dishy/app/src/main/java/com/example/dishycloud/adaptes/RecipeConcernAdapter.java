package com.example.dishycloud.adaptes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.models.ItemsQuickSearch;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeConcernAdapter extends RecyclerView.Adapter<RecipeConcernAdapter.ViewHolder> {
    private List<ItemsQuickSearch> itemsQuickSearchList;
    private Context context;

    public RecipeConcernAdapter(Context context, List<ItemsQuickSearch> itemsQuickSearchList) {
        this.itemsQuickSearchList = itemsQuickSearchList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =  LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_concern, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameQuick.setText(itemsQuickSearchList.get(position).getName());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.build()
                .load(itemsQuickSearchList.get(position).getImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.mIconConcern);
        holder.mIconConcern.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public int getItemCount() {
        return itemsQuickSearchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView  mIconConcern;
        private TextView nameQuick;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nameQuick = itemView.findViewById(R.id.name_quick_search);
            mIconConcern = itemView.findViewById(R.id.icon_concern);
        }
    }
}
