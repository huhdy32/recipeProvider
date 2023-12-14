package com.example.recipe_provider.dto;

public class Ingredient {
    private long id;
    private String name;
    private int remain;
    private String imagePath;

    public Ingredient(final long id, final String name, final int remain, final String imagePath) {
        this.id = id;
        this.name = name;
        this.remain = remain;
        this.imagePath = imagePath;
    }

    public Ingredient(final String name, final int remain, final String imagePath) {
        this.name = name;
        this.remain = remain;
        this.imagePath = imagePath;
    }

    public long getId() {
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
