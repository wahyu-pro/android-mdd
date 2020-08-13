package com.wahyu.challengefourmaterialdesign.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.wahyu.challengefourmaterialdesign.R;
import com.wahyu.challengefourmaterialdesign.adapters.ProductAdapter;
import com.wahyu.challengefourmaterialdesign.clients.ApiClient;
import com.wahyu.challengefourmaterialdesign.databinding.ActivityThirdBinding;
import com.wahyu.challengefourmaterialdesign.models.Products;
import com.wahyu.challengefourmaterialdesign.services.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity {

    ActivityThirdBinding binding;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_third);
        adapter = new ProductAdapter();
        getProducts();

    }

    private void getProducts() {
        ApiClient.client(ProductService.class, "https://fakestoreapi.com/")
                .getProducts().enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                generateData(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });
    }

    private void generateData(List<Products> products){
        adapter.setProducts(products);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        binding.recyclerView.setAdapter(adapter);
    }
}