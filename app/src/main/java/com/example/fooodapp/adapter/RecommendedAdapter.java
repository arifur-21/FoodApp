package com.example.fooodapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooodapp.R;
import com.example.fooodapp.model.Recommended;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder> implements Filterable {
private List<Recommended> recommendedList;
private List<Recommended> list;
private Context context;

    public RecommendedAdapter(List<Recommended> recommendedList, Context context) {
        this.recommendedList = recommendedList;
        this.context = context;
        list = new ArrayList<>(recommendedList);
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommended_recycleview, parent, false);
        return new RecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, int position) {

        holder.recommendedName.setText(recommendedList.get(position).getName());
        holder.recommendedRating.setText(recommendedList.get(position).getRating());
        holder.recommendedCharges.setText(recommendedList.get(position).getDeliveryCharges());
        holder.recommendedDeliveryTime.setText(recommendedList.get(position).getDeliveryTime());
        holder.recommendedPrice.setText("$ "+recommendedList.get(position).getPrice());

        Glide.with(context).load(recommendedList.get(position).getImageUrl()).into(holder.recommendedImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name",recommendedList.get(position).getName());
                bundle.putString("price",recommendedList.get(position).getPrice());
                bundle.putString("rating",recommendedList.get(position).getRating());
                bundle.putString("image",recommendedList.get(position).getImageUrl());

                Navigation.findNavController(v).navigate(R.id.action_homePage_to_detailsFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    @Override
    public Filter getFilter() {

        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String searchText = constraint.toString().toLowerCase();

            List<Recommended> recommendedList = new ArrayList<>();

            if (searchText.length()==0 || searchText.isEmpty()){
                recommendedList.addAll(list);
            }
            else {
                for (Recommended item:list)
                {
                    if (item.getImageUrl().contains(searchText))
                    {
                        recommendedList.add(item);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values=recommendedList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            list.clear();
            list.addAll((Collection< ? extends  Recommended>) results.values);
            notifyDataSetChanged();
        }
    };

    public class RecommendedViewHolder extends RecyclerView.ViewHolder {
        ImageView recommendedImage;
        TextView recommendedName, recommendedRating, recommendedDeliveryTime, recommendedCharges, recommendedPrice;
        public RecommendedViewHolder(@NonNull View itemView) {
            super(itemView);

            recommendedImage = itemView.findViewById(R.id.recommended_image);
            recommendedName = itemView.findViewById(R.id.recommended_name);
            recommendedRating = itemView.findViewById(R.id.recommended_rating);
            recommendedDeliveryTime = itemView.findViewById(R.id.recommended_delivery_time);
            recommendedCharges = itemView.findViewById(R.id.delivery_type);
            recommendedCharges = itemView.findViewById(R.id.delivery_type);
            recommendedPrice = itemView.findViewById(R.id.recommended_price);
        }
    }
}
