package com.example.dishycloud.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Follower implements Serializable {
    @SerializedName("author")
    private String author;
    @SerializedName("follower")
    private String follower;

    public Follower(String author, String follower) {
        this.author = author;
        this.follower = follower;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }
}
