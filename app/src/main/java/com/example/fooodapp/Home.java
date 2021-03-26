package com.example.fooodapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.fooodapp.adapter.AllMenuAdapter;
import com.example.fooodapp.adapter.PopularItemAdapter;
import com.example.fooodapp.adapter.RecommendedAdapter;
import com.example.fooodapp.databinding.FragmentHomePageBinding;
import com.example.fooodapp.model.Allmenu;
import com.example.fooodapp.model.FoodData;
import com.example.fooodapp.model.Popular;
import com.example.fooodapp.model.Recommended;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends Fragment {

        private FragmentHomePageBinding binding;
        private InterfaceApi interfaceApi;
        private AllMenuAdapter allMenuAdapter;
        private RecommendedAdapter recommendedAdapter;
        private PopularItemAdapter popularItemAdapter;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.searchbarId);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = FragmentHomePageBinding.inflate(inflater);
        interfaceApi = RefrofitClient.getRetrofitInstance().create(InterfaceApi.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Call<List<FoodData>> call = interfaceApi.getAllData();
        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {
                List<FoodData> foodDataList = response.body();

                getPopularData(foodDataList.get(0).getPopular());
                getRecommendedData(foodDataList.get(0).getRecommended());
                getAllMenu (foodDataList.get(0).getAllmenu());
            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                Toast.makeText(getActivity(), "Server is not Responsed", Toast.LENGTH_LONG).show();
            }
        });


    }



    private void getAllMenu(List<Allmenu> allmenu) {

         allMenuAdapter = new AllMenuAdapter(allmenu, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.allMenuRecycler.setAdapter(allMenuAdapter);
        binding.allMenuRecycler.setLayoutManager(layoutManager);
        allMenuAdapter.notifyDataSetChanged();
    }

    private void getRecommendedData(List<Recommended> recommended) {
        recommendedAdapter = new RecommendedAdapter(recommended, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false );
        binding.recommendedRecycler.setAdapter(recommendedAdapter);
        binding.recommendedRecycler.setLayoutManager(layoutManager);
        recommendedAdapter.notifyDataSetChanged();
    }

    private void getPopularData(List<Popular> popular) {

        popularItemAdapter = new PopularItemAdapter(popular, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.popularRecycler.setAdapter(popularItemAdapter);
        binding.popularRecycler.setLayoutManager(layoutManager);
        popularItemAdapter.notifyDataSetChanged();

    }
}