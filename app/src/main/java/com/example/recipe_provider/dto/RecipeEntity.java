package com.example.recipe_provider.dto;

import java.util.HashMap;
import java.util.Map;

public class RecipeEntity {
    private final long id;
    private final String name;
    private final Map<Ingredient, Integer> ingredients;

    public RecipeEntity(final long id, final String name, final HashMap<Ingredient, Integer> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public float getRate() {
        int counts = ingredients.size();
        float sum = 0;
        for (Ingredient ingredient : ingredients.keySet()) {
            if (ingredient.getRemain() == 0) {
                continue;
            }
            float temp = (float) ingredient.getRemain() / (float) ingredients.get(ingredient) * 100;
            if (temp > 100) {
                temp = 100;
            }
            sum += temp / counts;
        }
        return sum;
    }
}
