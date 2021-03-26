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
import com.example.fooodapp.model.Allmenu;

import java.util.List;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuViewHolder> {

   private List<Allmenu> allmenuList;
   private Context context;

    public AllMenuAdapter(List<Allmenu> allmenuList, Context context) {
        this.allmenuList = allmenuList;
        this.context = context;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_menu,parent,false);
        return new AllMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, int position) {

        holder.allMenuNme.setText(allmenuList.get(position).getName());
        holder.allMenuPrice.setText("$ "+allmenuList.get(position).getPrice());
        holder.allMenuTime.setText(allmenuList.get(position).getDeliveryTime());
        holder.allMenuReatin.setText(allmenuList.get(position).getRating());
        holder.allMenuCharges.setText(allmenuList.get(position).getDeliveryCharges());
        holder.allMenuNote.setText(allmenuList.get(position).getNote());

        Glide.with(context).load(allmenuList.get(position).getImageUrl()).into(holder.allMenuImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name",allmenuList.get(position).getName());
                bundle.putString("price",allmenuList.get(position).getPrice());
                bundle.putString("rating",allmenuList.get(position).getRating());
                bundle.putString("image",allmenuList.get(position).getImageUrl());

                Navigation.findNavController(v).navigate(R.id.action_homePage_to_detailsFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }

    public class AllMenuViewHolder extends RecyclerView.ViewHolder {
        TextView allMenuNme, allMenuNote, allMenuReatin, allMenuTime, allMenuCharges, allMenuPrice;
        ImageView allMenuImage;
        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            allMenuNme = itemView.findViewById(R.id.all_menu_name);
            allMenuNote = itemView.findViewById(R.id.all_menu_note);
            allMenuCharges = itemView.findViewById(R.id.all_menu_delivery_charge);
            allMenuReatin = itemView.findViewById(R.id.all_menu_rating);
            allMenuTime = itemView.findViewById(R.id.all_menu_deliverytime);
            allMenuPrice = itemView.findViewById(R.id.all_menu_price);
            allMenuImage = itemView.findViewById(R.id.all_menu_image);


        }
    }
}
