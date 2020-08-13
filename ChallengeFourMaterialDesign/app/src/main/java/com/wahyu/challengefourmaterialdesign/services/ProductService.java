package com.wahyu.challengefourmaterialdesign.services;

import com.wahyu.challengefourmaterialdesign.models.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("products")
    Call<List<Products>> getProducts();
}
