package com.example.app_shopping.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_shopping.R;
import com.example.app_shopping.adapter.CartAdapter;
import com.example.app_shopping.model.Product;
import com.example.app_shopping.utils.CartManager;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment {

    private RecyclerView rvCartItems;
    private TextView tvTotalPrice, tvEmptyCart;
    private Button btnCheckout;
    private CartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        rvCartItems = view.findViewById(R.id.rvCartItems);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        tvEmptyCart = view.findViewById(R.id.tvEmptyCart);
        btnCheckout = view.findViewById(R.id.btnCheckout);

        setupRecyclerView();
        updateCartUI();

        btnCheckout.setOnClickListener(v -> {
            if (CartManager.getInstance().getCartItems().isEmpty()) {
                Toast.makeText(getContext(), "Giỏ hàng của bạn đang trống", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Đang tiến hành thanh toán...", Toast.LENGTH_SHORT).show();
                // Logic cho thanh toán có thể thêm ở đây
            }
        });

        return view;
    }

    private void setupRecyclerView() {
        adapter = new CartAdapter(CartManager.getInstance().getCartItems(), new CartAdapter.OnCartItemChangeListener() {
            @Override
            public void onRemoveClick(Product product) {
                CartManager.getInstance().removeFromCart(product);
                updateCartUI();
                Toast.makeText(getContext(), "Đã xóa sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onQuantityChange(Product product, int newQuantity) {
                // Logic cập nhật số lượng
                updateCartUI();
            }
        });
        rvCartItems.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCartItems.setAdapter(adapter);
    }

    private void updateCartUI() {
        List<Product> items = CartManager.getInstance().getCartItems();
        if (items.isEmpty()) {
            tvEmptyCart.setVisibility(View.VISIBLE);
            rvCartItems.setVisibility(View.GONE);
        } else {
            tvEmptyCart.setVisibility(View.GONE);
            rvCartItems.setVisibility(View.VISIBLE);
        }

        adapter.setCartItems(items);
        
        double total = 0;
        for (Product p : items) {
            total += p.getPrice(); // Giả định số lượng là 1 để đơn giản hóa
        }
        
        // Định dạng tiền tệ VNĐ
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        tvTotalPrice.setText("Tổng cộng: " + formatter.format(total));
    }

    @Override
    public void onResume() {
        super.onResume();
        updateCartUI();
    }
}
