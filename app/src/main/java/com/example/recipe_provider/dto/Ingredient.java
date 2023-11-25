package com.example.recipe_provider.dto;

public class Ingredient {
    private int id;
    private String name;
    private int remain;
    private String imagePath;

    public Ingredient(int id, String name, int remain, String imagePath) {
        this.id = id;
        this.name = name;
        this.remain = remain;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getRemain() {
        return remain;
    }
    public String getImagePath() {
        return imagePath;
    }

}