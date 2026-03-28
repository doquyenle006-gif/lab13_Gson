package com.example.app_shopping.model;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String name;
    private String description;
    private String thumbnail;

    public Category(int id, String name, String description, String thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getThumbnail() { return thumbnail; }
}
