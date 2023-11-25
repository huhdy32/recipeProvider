package com.example.recipe_provider.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recipe_provider.dto.Ingredient;
import com.example.recipe_provider.dto.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeRepository {

    private final DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public RecipeRepository(Context context) {
        this.databaseHelper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, 1);
    }

    // 모든 레시피와 그 데이터 들을 리턴하는 메소드
    public List<Recipe> getAllRecipe() {
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.RECIPE_TABLE_NAME, null);
        List<Recipe> recipes = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    recipes.add(new Recipe(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            getRequireIngredient(cursor.getInt(0)),
                            cursor.getString(4)));
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        db.close();
        return recipes;
    }

    // 레시피 추가 기능
    public boolean addRecipe(Recipe recipe) {

    }

    public HashMap<Ingredient, Integer> getRequireIngredient(final int recipeIdx) {
        db = databaseHelper.getReadableDatabase();
        String query = "SELECT i.name, i.remain, ri.requirement " +
                "FROM " + DatabaseHelper.INGREDIENT_TABLE_NAME + " as i " +
                "INNER JOIN " + DatabaseHelper.RELATION_TABLE_NAME + " as ri ON i.id = ri.ingredient_id " +
                "WHERE ri.recipe_id = ?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(recipeIdx)});
        HashMap<Ingredient, Integer> map = new HashMap<>();

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Ingredient ingredient = new Ingredient(cursor.getString(0), cursor.getInt(1));
                    int requirement = cursor.getInt(2);
                    map.put(ingredient, requirement);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        return map;
    }
}
