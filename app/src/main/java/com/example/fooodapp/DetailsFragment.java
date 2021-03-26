package com.example.fooodapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.fooodapp.databinding.FragmentDetailsBinding;

import java.time.temporal.ValueRange;


public class DetailsFragment extends Fragment {

   private FragmentDetailsBinding binding;
   private String name, price,rating,image;

    public DetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
      name = bundle.getString("name");
      price = bundle.getString("price");
      rating = bundle.getString("rating");
      image = bundle.getString("image");

        Glide.with(getActivity()).load(image).into(binding.imageView5);
        binding.name.setText(name);
        binding.price.setText("$ "+price);
        binding.rating.setText(rating);
        binding.ratingBar.setRating(Float.parseFloat(rating));


    }
}