package com.example.dishycloud.models;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StepMake implements Serializable {
    @SerializedName("description")
    private String description;
    @SerializedName("isRepair")
    private boolean isRepair;
    @SerializedName("image1")
    private String image1;
    @SerializedName("image2")
    private String image2;
    @SerializedName("recipeId")
    private String recipeId;
    @SerializedName("video")
    private String video;
    private Uri urlImgeOne;
    private Uri urlImgWto;


    public StepMake(String descrip, boolean prepairStage, Uri imageOne, Uri imageTwo) {
        this.description = descrip;
        this.isRepair = prepairStage;
        this.urlImgeOne = imageOne;
        this.urlImgWto = imageTwo;
    }

    public StepMake(String descrip, boolean prepairStage, String urlImgeOne, String urlImgWto) {
        this.description = descrip;
        this.isRepair = prepairStage;
        this.image1 = urlImgeOne;
        this.image2 = urlImgWto;
    }

    public StepMake(String description, boolean isRepair, String image1, String image2, Uri urlImgeOne, Uri urlImgWto) {
        this.description = description;
        this.isRepair = isRepair;
        this.image1 = image1;
        this.image2 = image2;
        this.urlImgeOne = urlImgeOne;
        this.urlImgWto = urlImgWto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRepair() {
        return isRepair;
    }

    public void setRepair(boolean repair) {
        isRepair = repair;
    }

    public Uri getUrlImgeOne() {
        return urlImgeOne;
    }

    public void setUrlImgeOne(Uri urlImgeOne) {
        this.urlImgeOne = urlImgeOne;
    }

    public Uri getUrlImgWto() {
        return urlImgWto;
    }

    public void setUrlImgWto(Uri urlImgWto) {
        this.urlImgWto = urlImgWto;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
