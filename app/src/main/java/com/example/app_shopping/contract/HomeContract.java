package com.example.app_shopping.contract;

import com.example.app_shopping.model.Category;
import com.example.app_shopping.model.Product;

import java.util.List;

public interface HomeContract {
    interface View {
        void showProducts(List<Product> products);
        void showCategories(List<Category> categories);
        void showError(String message);
        void showLoading();
        void hideLoading();
    }

    interface Presenter {
        void fetchProducts();
        void fetchCategories();
        void searchProducts(String query);
        void filterProductsByCategory(int categoryId);
    }
}
