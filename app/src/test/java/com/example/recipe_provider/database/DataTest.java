package com.example.recipe_provider.database;

import static org.junit.Assert.*;

import androidx.test.core.app.*;
import android.content.Context;

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
        assertEquals(this.recipeRepository.getAll().size(), 1);
    }
    @Test
    public void TEST_INGREDIENT_TABLE() {
        ingredientRepository = new IngredientRepository(context);
        assertEquals(this.ingredientRepository.getAll().size(), 3);
    }
    @Test
    public void TEST_GET_INGREDIENT() {
        ingredientRepository = new IngredientRepository(context);
        assertEquals(ingredientRepository.get(1).getName(), "밥");
        assertEquals(ingredientRepository.get(2).getName(), "간장");
    }

    @Test
    public void TEST_GET_REQUIREMENT () {
        recipeRepository = new RecipeRepository(context);
        assertEquals(recipeRepository.getRequireIngredient(1).size(), 3);
    }

}
