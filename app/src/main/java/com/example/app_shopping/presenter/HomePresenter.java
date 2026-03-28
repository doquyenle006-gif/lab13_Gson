package com.example.app_shopping.presenter;

import com.example.app_shopping.R;
import com.example.app_shopping.contract.HomeContract;
import com.example.app_shopping.model.Category;
import com.example.app_shopping.model.Product;
import com.example.app_shopping.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private List<Product> allProducts = new ArrayList<>();

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void fetchProducts() {
        view.showLoading();
        RetrofitClient.getApiService().getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    allProducts = response.body();
                    view.showProducts(allProducts);
                } else {
                    loadMockProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                view.hideLoading();
                loadMockProducts();
            }
        });
    }

    private void loadMockProducts() {
        allProducts = new ArrayList<>();
        String resPath = "android.resource://com.example.app_shopping/";
        
        allProducts.add(new Product(1, "Nike Air Max 2024", "Giày chạy bộ cực êm, thoáng khí, phù hợp mọi địa hình.", resPath + R.drawable.anhgiay, 3500000, 10, 1));
        allProducts.add(new Product(2, "Adidas Ultraboost", "Hoàn trả năng lượng tối đa cho mỗi bước chạy của bạn.", resPath + R.drawable.anhgiay1, 4200000, 5, 1));
        allProducts.add(new Product(3, "Puma Speed", "Thiết kế thời trang, nhẹ nhàng và vô cùng năng động.", resPath + R.drawable.anhgiay2, 2800000, 8, 2));
        allProducts.add(new Product(4, "Bitis Hunter", "Nâng niu bàn chân Việt - Phiên bản đặc biệt màu cam.", resPath + R.drawable.anhgiay3, 950000, 20, 2));
        allProducts.add(new Product(5, "New Balance 550", "Phong cách retro cổ điển, cực kỳ dễ phối đồ dạo phố.", resPath + R.drawable.anhgiay4, 2500000, 15, 2));
        allProducts.add(new Product(6, "Asics Gel-Kayano", "Hỗ trợ tối ưu cho người chạy bộ đường dài chuyên nghiệp.", resPath + R.drawable.anhgiay5, 3800000, 7, 1));
        allProducts.add(new Product(7, "Converse Chuck 70", "Biểu tượng thời trang đường phố không bao giờ lỗi mốt.", resPath + R.drawable.anhgiay6, 1900000, 30, 2));
        allProducts.add(new Product(8, "Vans Old Skool", "Giày trượt ván bền bỉ, mang phong cách bụi bặm.", resPath + R.drawable.anhgiay7, 1750000, 25, 2));
        
        view.showProducts(allProducts);
    }

    @Override
    public void fetchCategories() {
        RetrofitClient.getApiService().getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.showCategories(response.body());
                } else {
                    loadMockCategories();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                loadMockCategories();
            }
        });
    }

    private void loadMockCategories() {
        List<Category> categories = new ArrayList<>();
        String resPath = "android.resource://com.example.app_shopping/";
        
        categories.add(new Category(1, "Chạy bộ", "Giày chuyên dụng cho chạy bộ", resPath + R.drawable.anhgiay5));
        categories.add(new Category(2, "Thời trang", "Giày phong cách hàng ngày", resPath + R.drawable.anhgiay4));
        view.showCategories(categories);
    }

    @Override
    public void searchProducts(String query) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
            }
        }
        view.showProducts(filteredList);
    }

    @Override
    public void filterProductsByCategory(int categoryId) {
        if (categoryId == -1) {
            view.showProducts(allProducts);
            return;
        }
        List<Product> filteredList = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getCategoryId() == categoryId) {
                filteredList.add(product);
            }
        }
        view.showProducts(filteredList);
    }
}
