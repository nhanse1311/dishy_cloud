package com.example.dishycloud.models;

import java.io.Serializable;
import java.util.List;

public class Chef implements Serializable {
    private String avatar;
    private String name;
    private int numberRecipes;
    private int numberLiker;
    private List<Dishy> dishyList;

    public Chef(String avatar, String name, int numberRecipes, int numberLiker, List<Dishy> dishyList) {
        this.avatar = avatar;
        this.name = name;
        this.numberRecipes = numberRecipes;
        this.numberLiker = numberLiker;
        this.dishyList = dishyList;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberRecipes() {
        return numberRecipes;
    }

    public void setNumberRecipes(int numberRecipes) {
        this.numberRecipes = numberRecipes;
    }

    public int getNumberLiker() {
        return numberLiker;
    }

    public void setNumberLiker(int numberLiker) {
        this.numberLiker = numberLiker;
    }

    public List<Dishy> getDishyList() {
        return dishyList;
    }

    public void setDishyList(List<Dishy> dishyList) {
        this.dishyList = dishyList;
    }
}
