package com.example.recipe_provider.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    public Ingredient get(int ingredientId) {
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
    public boolean insert(Ingredient ingredient) {
        db = databaseHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", ingredient.getName());
            contentValues.put("remain", ingredient.getRemain());
            contentValues.put("imagePath", ingredient.getImagePath());
            db.insert(DatabaseHelper.RECIPE_TABLE_NAME, null, contentValues);
            return true;
        } finally {
            db.close();
            return false;
        }
    }
}
