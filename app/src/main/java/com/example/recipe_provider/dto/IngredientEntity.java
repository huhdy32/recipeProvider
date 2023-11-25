package com.example.recipe_provider.dto;

public class IngredientEntity {
    private int id;
    private String name;
    private int remain;

    public IngredientEntity(int id, String name, int remain) {
        this.id = id;
        this.name = name;
        this.remain = remain;
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
}
