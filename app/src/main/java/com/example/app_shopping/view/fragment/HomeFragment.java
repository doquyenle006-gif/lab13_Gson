package com.example.app_shopping.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_shopping.R;
import com.example.app_shopping.adapter.CategoryAdapter;
import com.example.app_shopping.adapter.ProductAdapter;
import com.example.app_shopping.contract.HomeContract;
import com.example.app_shopping.model.Category;
import com.example.app_shopping.model.Product;
import com.example.app_shopping.presenter.HomePresenter;
import com.example.app_shopping.view.activity.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View {

    private RecyclerView rvCategories, rvProducts;
    private EditText etSearch;
    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;
    private HomePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvCategories = view.findViewById(R.id.rvCategories);
        rvProducts = view.findViewById(R.id.rvProducts);
        etSearch = view.findViewById(R.id.etSearch);

        setupRecyclerViews();
        setupSearch();

        presenter = new HomePresenter(this);
        presenter.fetchCategories();
        presenter.fetchProducts();

        return view;
    }

    private void setupRecyclerViews() {
        categoryAdapter = new CategoryAdapter(new ArrayList<>(), category -> {
            presenter.filterProductsByCategory(category.getId());
        });
        rvCategories.setAdapter(categoryAdapter);

        productAdapter = new ProductAdapter(new ArrayList<>(), product -> {
            Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
            intent.putExtra("product", product);
            startActivity(intent);
        });
        rvProducts.setAdapter(productAdapter);
    }

    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.searchProducts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public void showProducts(List<Product> products) {
        productAdapter.setProducts(products);
    }

    @Override
    public void showCategories(List<Category> categories) {
        // Add "All" category manually if needed
        categoryAdapter.setCategories(categories);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        // Show loading state if UI element exists
    }

    @Override
    public void hideLoading() {
        // Hide loading state
    }
}
