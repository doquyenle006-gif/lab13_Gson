package com.example.app_shopping.network;

import com.example.app_shopping.model.Category;
import com.example.app_shopping.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products")
    Call<List<Product>> getProducts();

    @GET("categories")
    Call<List<Category>> getCategories();
}
