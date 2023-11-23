package com.example.recipe_provider.domain;

public class Ingredient {
    private String name;
    private int remain;
    private String image;

    public Ingredient(String name, int remain) {
        this.name = name;
        this.remain = remain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }
}
