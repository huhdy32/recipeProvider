package com.example.recipe_provider.dto;

public class IngredientEntity {
    private final long id;
    private final String name;
    private final int remain;

    public IngredientEntity(final long id, final String name, final int remain) {
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
