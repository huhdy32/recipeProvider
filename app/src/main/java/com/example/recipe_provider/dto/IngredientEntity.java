package com.example.recipe_provider.dto;

public class IngredientEntity {
    private long id;
    private String name;
    private int remain;

    public IngredientEntity(long id, String name, int remain) {
        this.id = id;
        this.name = name;
        this.remain = remain;
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
}
