package com.example.recipe_provider.dto;

import java.util.HashMap;

public class Recipe {
    private long id;
    private String name;
    private String details;
    private String imagePath;
    private HashMap<Ingredient, Integer> ingredientRequirements;
    // 이 레시피 를 만드는 데 필요한 재료와 그 양이 <Key, Value>로 저장됨
    private String recipeType;

    public Recipe(final long id, final String name, final String details, final String imagePath, final HashMap<Ingredient, Integer> ingredientRequirements, final String recipeType) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.imagePath = imagePath;
        this.ingredientRequirements = ingredientRequirements;
        this.recipeType = recipeType;
    }

    public Recipe(final String name, final String details, final String imagePath, final HashMap<Ingredient, Integer> ingredientRequirements, final String recipeType) {
        this.name = name;
        this.details = details;
        this.imagePath = imagePath;
        this.ingredientRequirements = ingredientRequirements;
        this.recipeType = recipeType;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getImagePath() {
        return imagePath;
    }

    public HashMap<Ingredient, Integer> getIngredientRequirements() {
        return ingredientRequirements;
    }

    public String getRecipeType() {
        return recipeType;
    }
}
