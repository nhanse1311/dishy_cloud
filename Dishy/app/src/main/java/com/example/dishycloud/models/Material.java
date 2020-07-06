package com.example.dishycloud.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Material implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("quantity")
    private double quantity;
    @SerializedName("unit")
    private String unit;

    public Material(String name, double quality, String unit) {
        this.name = name;
        this.quantity = quality;
        this.unit = unit;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuality() {
        return quantity;
    }

    public void setQuality(double quality) {
        this.quantity = quality;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
