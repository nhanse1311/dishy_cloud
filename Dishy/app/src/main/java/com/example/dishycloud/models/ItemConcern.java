package com.example.dishycloud.models;

public class ItemConcern {
    private int img;
    private String name;

    public ItemConcern() {}

    public ItemConcern(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
