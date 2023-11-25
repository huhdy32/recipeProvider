package com.example.recipe_provider.dto;

public class RecipeEntity {
    private int id;
    private String name;
    private int remain;
    private int require;

    public RecipeEntity(int id, String name, int remain, int require) {
        this.id = id;
        this.name = name;
        this.remain = remain;
        this.require = require;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public float getRate() {
        return (float) remain / (float) require;
    }
}
