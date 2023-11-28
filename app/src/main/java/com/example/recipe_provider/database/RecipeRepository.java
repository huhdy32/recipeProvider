package com.example.recipe_provider.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recipe_provider.dto.Ingredient;
import com.example.recipe_provider.dto.Recipe;
import com.example.recipe_provider.dto.RecipeEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeRepository {

    private final DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public RecipeRepository(Context context) {
        this.databaseHelper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, 1);
    }

    // 리스트형태로 반환되는 데이터를 위함
    public List<RecipeEntity> getAll() {
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT r.id, r.name FROM " + DatabaseHelper.RECIPE_TABLE_NAME
                + " as r", null);
        List<RecipeEntity> recipes = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    recipes.add(new RecipeEntity(
                            cursor.getInt(0),
                            cursor.getString(1),
                            getRequireIngredient(cursor.getInt(0)).keySet().stream()
                                    .mapToInt(Ingredient::getRemain)
                                    .sum(),
                            getRequireIngredient(cursor.getInt(0)).values().stream()
                                    .mapToInt(o -> o)
                                    .sum()
                    ));
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        cursor.close();
        db.close();
        return recipes;
    }

    public Recipe get(final long recipeId) {
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT r.id, r.name, r.details, r.imagePath, r.recipeType FROM "
                + DatabaseHelper.RECIPE_TABLE_NAME + "as r WHERE id = " + recipeId, null);
        Recipe recipe;
        if (cursor.moveToFirst()) {
            recipe = new Recipe(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    getRequireIngredient(cursor.getInt(0)),
                    cursor.getString(4)
            );
        } else {
            recipe = null;
        }
        cursor.close();
        db.close();
        return recipe;
    }

    // 레시피 추가 기능
    public void insert(final Recipe recipe) {
        db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", recipe.getName());
        contentValues.put("details", recipe.getDetails());
        contentValues.put("imagePath", recipe.getImagePath());
        contentValues.put("recipeType", recipe.getRecipeType());
        if (-1 == db.insert(DatabaseHelper.RECIPE_TABLE_NAME, null, contentValues)) {
            db.close();
            throw new NullPointerException("잘못된 형식 입니다.");
        }

        for (Ingredient ingredient : recipe.getIngredientRequirements().keySet()) {
            contentValues = new ContentValues();
            contentValues.put("recipe_id", recipe.getId());
            contentValues.put("ingredient_id", ingredient.getId());
            contentValues.put("requirement", recipe.getIngredientRequirements().get(ingredient));
            if (-1 == db.insert(DatabaseHelper.RELATION_TABLE_NAME, null, contentValues)) {
                db.close();
                throw new IllegalArgumentException("없는 재료 입니다.");
            }
        }
        db.close();
        ;
    }

    public int delete(final long recipeId) {
        db = databaseHelper.getWritableDatabase();
        int count = db.delete(DatabaseHelper.RECIPE_TABLE_NAME,
                "id = ?", new String[]{String.valueOf(recipeId)});
        db.close();
        return count;
    }

    //사용자가 레시피 만들기를 클릭했을떄 일어나는 일
    public boolean create(final Recipe recipe) {
        db = databaseHelper.getWritableDatabase();
        Map<Ingredient, Integer> map = recipe.getIngredientRequirements();
        for (Ingredient ingredient : map.keySet()) {
            ContentValues contentValues = new ContentValues();
            int value = ingredient.getRemain() - map.get(ingredient);
            if (value < 0) {
                value = 0;
            }
            contentValues.put("remain", value);
            if (-1 == db.update(DatabaseHelper.INGREDIENT_TABLE_NAME, contentValues,
                    "id = ? ", new String[]{String.valueOf(ingredient.getId())})) {
                db.close();
                return false;
            }
        }
        db.close();
        return true;
    }

    // 레시피에 필요한 재료들과 각각에 대해 필요한 양을
    public HashMap<Ingredient, Integer> getRequireIngredient(final long recipeIdx) {
        db = databaseHelper.getReadableDatabase();
        String query = "SELECT i.id, i.name, i.remain, i.imagePath, ri.requirement " +
                "FROM " + DatabaseHelper.INGREDIENT_TABLE_NAME + " as i " +
                "INNER JOIN " + DatabaseHelper.RELATION_TABLE_NAME +
                " as ri ON i.id = ri.ingredient_id " +
                "WHERE ri.recipe_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(recipeIdx)});
        HashMap<Ingredient, Integer> map = new HashMap<>();

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Ingredient ingredient = new Ingredient(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3)
                    );
                    int requirement = cursor.getInt(4);
                    map.put(ingredient, requirement);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        db.close();
        cursor.close();
        return map;
    }
}
