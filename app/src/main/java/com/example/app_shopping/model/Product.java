package com.example.app_shopping.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String description;
    private String thumbnail;
    private double price;
    private int quantity;
    @SerializedName("category_id")
    private int categoryId;

    public Product(int id, String name, String description, String thumbnail, double price, int quantity, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getThumbnail() { return thumbnail; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getCategoryId() { return categoryId; }
}
