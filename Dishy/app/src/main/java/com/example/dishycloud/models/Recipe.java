package com.example.dishycloud.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("timeCook")
    private int timeCook;
    @SerializedName("levelRecipe")
    private int levelRecipe;
    @SerializedName("numberPeople")
    private int numberPeople;
    @SerializedName("materials")
    private List<Material> materials;
    @SerializedName("steps")
    private List<StepMake> steps;

    public Recipe(String name, String description, String image, int timeCook, int levelRecipe, int numberPeople, List<Material> materials, List<StepMake> steps) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.timeCook = timeCook;
        this.levelRecipe = levelRecipe;
        this.numberPeople = numberPeople;
        this.materials = materials;
        this.steps = steps;
    }

    public Recipe(String name, String description, String image, int timeCook, int levelRecipe, int numberPeople) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.timeCook = timeCook;
        this.levelRecipe = levelRecipe;
        this.numberPeople = numberPeople;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTimeCook() {
        return timeCook;
    }

    public void setTimeCook(int timeCook) {
        this.timeCook = timeCook;
    }

    public int getLevelRecipe() {
        return levelRecipe;
    }

    public void setLevelRecipe(int levelRecipe) {
        this.levelRecipe = levelRecipe;
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<StepMake> getSteps() {
        return steps;
    }

    public void setSteps(List<StepMake> steps) {
        this.steps = steps;
    }
}
