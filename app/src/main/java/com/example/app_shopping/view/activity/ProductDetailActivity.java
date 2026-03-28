package com.example.app_shopping.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.app_shopping.R;
import com.example.app_shopping.model.Product;
import com.example.app_shopping.utils.CartManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView ivProductDetail;
    private TextView tvDetailName, tvDetailPrice, tvDetailDescription;
    private ExtendedFloatingActionButton fabAddToCart;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ivProductDetail = findViewById(R.id.ivProductDetail);
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        fabAddToCart = findViewById(R.id.fabAddToCart);
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // Xử lý getSerializableExtra cho các phiên bản Android khác nhau
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            product = getIntent().getSerializableExtra("product", Product.class);
        } else {
            product = (Product) getIntent().getSerializableExtra("product");
        }

        if (product != null) {
            displayProductDetails();
        }

        fabAddToCart.setOnClickListener(v -> {
            CartManager.getInstance().addToCart(product);
            Toast.makeText(this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        });
    }

    private void displayProductDetails() {
        collapsingToolbar.setTitle(product.getName());
        tvDetailName.setText(product.getName());
        
        // Định dạng tiền tệ VNĐ
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        tvDetailPrice.setText(formatter.format(product.getPrice()));

        tvDetailDescription.setText(product.getDescription());

        Glide.with(this)
                .load(product.getThumbnail())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(ivProductDetail);
    }
}
