package com.example.app_shopping.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_shopping.R;
import com.example.app_shopping.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> cartItems;
    private OnCartItemChangeListener listener;

    public interface OnCartItemChangeListener {
        void onRemoveClick(Product product);
        void onQuantityChange(Product product, int newQuantity);
    }

    public CartAdapter(List<Product> cartItems, OnCartItemChangeListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvProductPrice.setText(String.format("$%.2f", product.getPrice()));
        // In a real app, you'd have a separate cart model with quantity. 
        // For simplicity, we use the product's quantity or assume 1.
        holder.tvQuantity.setText("1"); 

        Glide.with(holder.itemView.getContext())
                .load(product.getThumbnail())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(holder.ivProduct);

        holder.btnRemove.setOnClickListener(v -> {
            if (listener != null) listener.onRemoveClick(product);
        });

        holder.btnPlus.setOnClickListener(v -> {
            // Simplified quantity logic
        });

        holder.btnMinus.setOnClickListener(v -> {
            // Simplified quantity logic
        });
    }

    @Override
    public int getItemCount() {
        return cartItems != null ? cartItems.size() : 0;
    }

    public void setCartItems(List<Product> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvProductName, tvProductPrice, tvQuantity;
        ImageButton btnPlus, btnMinus, btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivCartProduct);
            tvProductName = itemView.findViewById(R.id.tvCartProductName);
            tvProductPrice = itemView.findViewById(R.id.tvCartProductPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
