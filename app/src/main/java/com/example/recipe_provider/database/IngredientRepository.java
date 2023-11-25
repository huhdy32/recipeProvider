package com.example.recipe_provider.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.RequiresPermission;

import com.example.recipe_provider.dto.Ingredient;
import com.example.recipe_provider.dto.IngredientEntity;

import java.util.ArrayList;
import java.util.List;

public class IngredientRepository {

    private final DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public IngredientRepository(Context context) {
        this.databaseHelper = new DatabaseHelper(context, DatabaseHelper.DATABASE_NAME, null, 1);
    }

    // 목록 출력을 위한 메소드
    public List<IngredientEntity> getAll() {
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT i.id, i.name, i.remain FROM " + DatabaseHelper.INGREDIENT_TABLE_NAME + " as i", null);
        List<IngredientEntity> ingredientEntities = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    ingredientEntities.add(new IngredientEntity(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2)
                    ));
                } while (cursor.moveToNext());
            }
        }
        db.close();
        cursor.close();
        return ingredientEntities;
    }

    // 목록의 엔터티를 클릭했을 때 재료 출력을 위한 메소드
    public Ingredient get(final long ingredientId) {
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.INGREDIENT_TABLE_NAME + " WHERE id = " + ingredientId, null);
        Ingredient ingredient;
        if (cursor.moveToFirst()) {
            ingredient = new Ingredient(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(3),
                    cursor.getString(2)
            );
        } else {
            ingredient = null;
        }
        cursor.close();
        db.close();
        return ingredient;
    }

    public long insert(final Ingredient ingredient) {
        db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", ingredient.getName());
        contentValues.put("remain", ingredient.getRemain());
        contentValues.put("imagePath", ingredient.getImagePath());
        long rowIdx = db.insert(DatabaseHelper.INGREDIENT_TABLE_NAME, null, contentValues);
        db.close();
        return rowIdx;
    }

    public int delete(final long ingredientId) {
        db = databaseHelper.getWritableDatabase();
        int count = db.delete(DatabaseHelper.INGREDIENT_TABLE_NAME, "id = ?", new String[] {String.valueOf(ingredientId)});
        db.close();
        return count;
    }
}
