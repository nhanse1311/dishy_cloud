package com.example.dishycloud.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    @SerializedName("id")
    private String id;
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
    @SerializedName("liked")
    private int liked;
    @SerializedName("disliked")
    private int disliked;
    @SerializedName("createBy")
    private String createBy;
    @SerializedName("materials")
    private List<Material> materials;
    @SerializedName("steps")
    private List<StepMake> steps;

    private String imagePath;


    public Recipe(String id, String name, String description, String image, int timeCook, int levelRecipe, int numberPeople, int liked, int disliked, String createBy, List<Material> materials, List<StepMake> steps) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.timeCook = timeCook;
        this.levelRecipe = levelRecipe;
        this.numberPeople = numberPeople;
        this.liked = liked;
        this.disliked = disliked;
        this.createBy = createBy;
        this.materials = materials;
        this.steps = steps;
    }

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

    public Recipe(String name, String description, String image, String imagePath,int timeCook, int levelRecipe, int numberPeople) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.imagePath  = imagePath;
        this.timeCook = timeCook;
        this.levelRecipe = levelRecipe;
        this.numberPeople = numberPeople;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getDisliked() {
        return disliked;
    }

    public void setDisliked(int disliked) {
        this.disliked = disliked;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
