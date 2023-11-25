package com.example.recipe_provider.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.example.recipe_provider.dto.Ingredient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)
public class IngredientRepositoryTest {
    Context context;

    @Before
    public void init() {
        context = ApplicationProvider.getApplicationContext();
    }
    @Test
    public void TEST_GET_ALL_INGREDIENTS() {
        IngredientRepository ingredientRepository = new IngredientRepository(context);
        assertEquals(ingredientRepository.getAll().size(), 3);
    }

    @Test
    public void TEST_INSERT_INGREDIENTS() {
        IngredientRepository ingredientRepository = new IngredientRepository(context);
        assertEquals(ingredientRepository.insert(new Ingredient(
                "heellowmd",
                2234,
                "heelo/1231")),4 );
    }

}
