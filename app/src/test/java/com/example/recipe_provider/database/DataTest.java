package com.example.recipe_provider.database;

import static org.junit.Assert.*;

import androidx.test.core.app.*;
import android.content.Context;

import com.example.recipe_provider.dto.Ingredient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)
public class DataTest {
    Context context;
    IngredientRepository ingredientRepository;
    RecipeRepository recipeRepository;
    @Before
    public void initTest() {
        context = ApplicationProvider.getApplicationContext();
    }
    @Test
    public void TEST_RECIPE_TABLE() {
        recipeRepository = new RecipeRepository(context);
        assertEquals(this.recipeRepository.getAllRecipe().size(), 1);
    }

    @Test
    public void TEST_GET_RECIPE_ID() {
        recipeRepository = new RecipeRepository(context);
        assertEquals(recipeRepository.getRecipeId("간장계란밥"), 1);
    }

    @Test
    public void TEST_INGREDIENT_TABLE() {
        ingredientRepository = new IngredientRepository(context);
        assertEquals(this.ingredientRepository.getAllIngredients().size(), 3);
    }

    @Test
    public void TEST_GET_INGREDIENT() {
        ingredientRepository = new IngredientRepository(context);
        assertEquals(ingredientRepository.getIngredient("간장").getName(), "간장");
    }

    @Test
    public void TEST_GET_REQUIREMENT () {
        recipeRepository = new RecipeRepository(context);
        assertEquals(recipeRepository.getRecipe(1).size(), 3);
    }
}
