package com.example.recipe_provider.dto;

import java.util.HashMap;

public class Recipe {
    private String name;
    private String details;
    private String image;
    private HashMap<Ingredient, Integer> ingredientRequirements;
    // 이 레시피 를 만드는 데 필요한 재료와 그 양이 <Key, Value>로 저장됨
    private String recipeType;

    public Recipe(String name, String details, String image, HashMap<Ingredient, Integer> ingredientRequirements, String recipeType) {
        this.name = name;
        this.details = details;
        this.image = image;
        this.ingredientRequirements = ingredientRequirements;
        this.recipeType = recipeType;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getImage() {
        return image;
    }

    public HashMap<Ingredient, Integer> getIngredientRequirements() {
        return ingredientRequirements;
    }

    public String getRecipeType() {
        return recipeType;
    }
}
