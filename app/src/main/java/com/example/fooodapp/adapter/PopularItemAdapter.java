package com.example.fooodapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooodapp.R;
import com.example.fooodapp.model.Popular;

import java.util.ArrayList;
import java.util.List;

public class PopularItemAdapter extends RecyclerView.Adapter<PopularItemAdapter.popularViewHolder> {

    private List<Popular> popularList;
    private Context context;

    public PopularItemAdapter(List<Popular> popularList, Context context) {
        this.popularList = popularList;
        this.context = context;
    }

    @NonNull
    @Override
    public popularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_recycleview,parent,false);

        return new popularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull popularViewHolder holder, int position) {

        holder.popularName.setText(popularList.get(position).getName());
        Glide.with(context).load(popularList.get(position).getImageUrl()).into(holder.popularImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name",popularList.get(position).getName());
                bundle.putString("price",popularList.get(position).getPrice());
                bundle.putString("rating",popularList.get(position).getRating());
                bundle.putString("image",popularList.get(position).getImageUrl());

                Navigation.findNavController(v).navigate(R.id.action_homePage_to_detailsFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public class popularViewHolder extends RecyclerView.ViewHolder {
        private ImageView popularImage;
        TextView popularName;
        public popularViewHolder(@NonNull View itemView) {
            super(itemView);
            popularImage = itemView.findViewById(R.id.all_menu_image);
            popularName = itemView.findViewById(R.id.all_menu_name);


        }
    }
}
