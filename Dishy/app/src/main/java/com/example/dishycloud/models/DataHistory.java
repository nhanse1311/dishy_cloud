package com.example.dishycloud.models;

public class DataHistory {
    private String image;
    private String nameFood;
    private String likeNumber;
    private String timeNumber;
    private String level;
    private String status;
    private String time;
    private int like;


    public DataHistory(String image, String nameFood, String likeNumber, String timeNumber,
                       String level, String status, String time, int like) {
        this.image = image;
        this.nameFood = nameFood;
        this.likeNumber = likeNumber;
        this.timeNumber = timeNumber;
        this.level = level;
        this.status = status;
        this.time = time;
        this.like = like;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public DataHistory() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getTimeNumber() {
        return timeNumber;
    }

    public void setTimeNumber(String timeNumber) {
        this.timeNumber = timeNumber;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
