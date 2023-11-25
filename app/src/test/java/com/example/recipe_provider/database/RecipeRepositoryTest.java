package com.example.recipe_provider.database;

import static org.junit.Assert.*;

import androidx.test.core.app.*;
import android.content.Context;

import com.example.recipe_provider.dto.Recipe;
import com.example.recipe_provider.dto.RecipeEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.HashMap;
import java.util.List;


@RunWith(RobolectricTestRunner.class)
public class RecipeRepositoryTest {
    Context context;

    @Before
    public void init() {
        context = ApplicationProvider.getApplicationContext();
    }
    @Test
    public void TEST_GET_ALL_RECIPE() {
        RecipeRepository recipeRepository = new RecipeRepository(context);
        List<RecipeEntity> recipeEntries = recipeRepository.getAll();
        RecipeEntity recipe = recipeEntries.get(0);
        assertTrue(recipe.getRate() > 100);
    }

    @Test
    public void TEST_INSERT_RECIPE() {
        RecipeRepository recipeRepository = new RecipeRepository(context);
        recipeRepository.insert(new Recipe(
                "국수",
                "말아먹으면 됨",
                "docs/README.md",
                new HashMap<>(),
                "한식"
        ));
        assertEquals(recipeRepository.getAll().size(), 2);
    }
    @Test
    public void TEST_DELETE_RECIPE() {
        RecipeRepository recipeRepository = new RecipeRepository(context);
        assertEquals(recipeRepository.delete(1), 1);
    }
}
