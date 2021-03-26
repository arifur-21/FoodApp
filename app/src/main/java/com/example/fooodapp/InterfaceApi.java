package com.example.fooodapp;

import com.example.fooodapp.model.FoodData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceApi {
    @GET("fooddata.json")
    Call<List<FoodData>> getAllData();

}
