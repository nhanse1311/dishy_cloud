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
import com.example.dishycloud.models.ItemsQuickSearch;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuickSearchAdapter extends RecyclerView.Adapter<QuickSearchAdapter.ViewHolder> {
    private List<ItemsQuickSearch> itemsQuickSearchList;
    private Context context;

    public QuickSearchAdapter(List<ItemsQuickSearch> itemsQuickSearchList, Context context) {
        this.itemsQuickSearchList = itemsQuickSearchList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =  LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.quick_search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuickSearchAdapter.ViewHolder holder, int position) {
        holder.nameQuick.setText(itemsQuickSearchList.get(position).getName());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.build().load(itemsQuickSearchList.get(position).getImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imageQuick);
    }

    @Override
    public int getItemCount() {
        return itemsQuickSearchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageQuick;
        private TextView nameQuick;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageQuick = itemView.findViewById(R.id.icon_quick_search);
            nameQuick = itemView.findViewById(R.id.name_quick_search);
        }
    }
}
