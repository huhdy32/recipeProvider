package com.example.recipe_provider.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recipe_provider.dto.Ingredient;
import com.example.recipe_provider.dto.Recipe;

import java.util.ArrayList;
import java.util.List;

public class IngredientRepository {

    private final DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public IngredientRepository(Context context) {
        this.databaseHelper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, 1);
    }

    public List<Recipe> getAllIngredients() {
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.INGREDIENT_TABLE_NAME, null);
        List<Recipe> recipes = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    recipes.add(new Recipe(cursor.getString(1), null, null, null, null));
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        return recipes;
    }

    public Ingredient getIngredient(String name) {
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.INGREDIENT_TABLE_NAME + " WHERE name = '" + name + "'", null);
        Ingredient ingredient;
        if (cursor.moveToFirst()) {
            ingredient = new Ingredient(cursor.getString(1), cursor.getInt(2));
        } else {
            ingredient = null;
        }
        cursor.close();
        db.close();
        return ingredient;
    }
}
