package com.example.dishycloud.models;

import java.io.Serializable;
import java.util.List;

public class Dishy implements Serializable {
    private String name;
    private String image;
    private String time;
    private int numberFavorite;
    private int eater;
    private int star;
    private String level;
    private List<StepMake> makes;
    private List<Material> materials;
    private Chef chef;

    public Dishy(String name, String image, String time, int eater, int numberFavorite, int star) {
        this.name = name;
        this.image = image;
        this.time = time;
        this.eater = eater;
        this.numberFavorite = numberFavorite;
        this.star = star;
    }

    public Dishy(String name, String image, String time, int star, String level, int numberFavorite, Chef chef) {
        this.name = name;
        this.image = image;
        this.time = time;
        this.star = star;
        this.level = level;
        this.numberFavorite = numberFavorite;
        this.chef = chef;
    }


    public Dishy(String name, String image, String time, int star, String level, int numberFavorite) {
        this.name = name;
        this.image = image;
        this.time = time;
        this.star = star;
        this.level = level;
        this.numberFavorite = numberFavorite;
    }

    public Dishy(String name, String image, String time, int numberFavorite, int eater, int star, String level, List<StepMake> makes, List<Material> materials) {
        this.name = name;
        this.image = image;
        this.time = time;
        this.numberFavorite = numberFavorite;
        this.eater = eater;
        this.star = star;
        this.level = level;
        this.makes = makes;
        this.materials = materials;
    }

    public Dishy(String name, String image, String time, int eater, int star, String level, int numberFavorite, List<StepMake> makes, List<Material> materials, Chef chef) {
        this.name = name;
        this.image = image;
        this.time = time;
        this.eater = eater;
        this.star = star;
        this.level = level;
        this.makes = makes;
        this.materials = materials;
        this.numberFavorite = numberFavorite;
        this.chef = chef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int liker) {
        this.star = liker;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<StepMake> getMakes() {
        return makes;
    }

    public void setMakes(List<StepMake> makes) {
        this.makes = makes;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public int getEater() {
        return eater;
    }

    public void setEater(int eater) {
        this.eater = eater;
    }

    public int getNumberFavorite() {
        return numberFavorite;
    }

    public void setNumberFavorite(int numberFavorite) {
        this.numberFavorite = numberFavorite;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }
}
