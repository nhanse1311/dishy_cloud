package com.example.dishycloud.models;

import java.io.Serializable;

public class Material implements Serializable {
    private String name;
    private double quality;
    private String unit;

    public Material(String name, double quality, String unit) {
        this.name = name;
        this.quality = quality;
        this.unit = unit;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
