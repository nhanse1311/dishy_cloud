package com.example.dishycloud.models;

public class ItemsQuickSearch {
    private String name;
    private String image;

    public ItemsQuickSearch(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public ItemsQuickSearch() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
