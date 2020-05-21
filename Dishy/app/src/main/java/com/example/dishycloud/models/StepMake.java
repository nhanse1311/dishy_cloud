package com.example.dishycloud.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class StepMake implements Serializable {
    private String descrip;
    private boolean prepairStage;
    private Bitmap imageOne;
    private Bitmap imageTwo;
    private String urlImgeOne;
    private String urlImgWto;


    public StepMake(String descrip, boolean prepairStage, Bitmap imageOne, Bitmap imageTwo) {
        this.descrip = descrip;
        this.prepairStage = prepairStage;
        this.imageOne = imageOne;
        this.imageTwo = imageTwo;
    }

    public StepMake(String descrip, boolean prepairStage, String urlImgeOne, String urlImgWto) {
        this.descrip = descrip;
        this.prepairStage = prepairStage;
        this.urlImgeOne = urlImgeOne;
        this.urlImgWto = urlImgWto;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public boolean isPrepairStage() {
        return prepairStage;
    }

    public void setPrepairStage(boolean prepairStage) {
        this.prepairStage = prepairStage;
    }

    public Bitmap getImageOne() {
        return imageOne;
    }

    public void setImageOne(Bitmap imageOne) {
        this.imageOne = imageOne;
    }

    public Bitmap getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(Bitmap imageTwo) {
        this.imageTwo = imageTwo;
    }

    public String getUrlImgeOne() {
        return urlImgeOne;
    }

    public void setUrlImgeOne(String urlImgeOne) {
        this.urlImgeOne = urlImgeOne;
    }

    public String getUrlImgWto() {
        return urlImgWto;
    }

    public void setUrlImgWto(String urlImgWto) {
        this.urlImgWto = urlImgWto;
    }
}
