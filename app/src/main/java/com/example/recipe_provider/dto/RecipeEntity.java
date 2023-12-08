package com.example.recipe_provider.dto;

public class RecipeEntity {
    private long id;
    private String name;
    private int remain;
    private int require;

    public RecipeEntity(long id, String name, int remain, int require) {
        this.id = id;
        this.name = name;
        this.remain = remain;
        this.require = require;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public float getRate() {
        float result = ((float) remain / (float) require) * 100;
        if (result > 100) {
            return 100;
        }
        return result;
    }
}
