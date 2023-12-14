package com.example.recipe_provider.dto;

import androidx.annotation.Nullable;

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

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Ingredient ingredient = (Ingredient) obj;
        return ingredient.getName().equals(name);
    }
}
