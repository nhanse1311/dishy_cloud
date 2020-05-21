package com.example.dishycloud.models;

public class ItemsResult {
    private String foodName;
    private String likeNumber;
    private String accountName;
    private String time;
    private String foodType;
    private String level;
    private int like;
    private String numRecipe;
    private String imageFood;
    private String avatar;

    public ItemsResult() {
    }

    public ItemsResult(String foodName, String likeNumber, String accountName, String time, String foodType, String level, int like, String numRecipe, String imageFood, String avatar) {
        this.foodName = foodName;
        this.likeNumber = likeNumber;
        this.accountName = accountName;
        this.time = time;
        this.foodType = foodType;
        this.level = level;
        this.like = like;
        this.numRecipe = numRecipe;
        this.imageFood = imageFood;
        this.avatar = avatar;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getNumRecipe() {
        return numRecipe;
    }

    public void setNumRecipe(String numRecipe) {
        this.numRecipe = numRecipe;
    }

    public String getImageFood() {
        return imageFood;
    }

    public void setImageFood(String imageFood) {
        this.imageFood = imageFood;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
