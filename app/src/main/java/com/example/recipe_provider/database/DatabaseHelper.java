package com.example.recipe_provider.database;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public static final String DATABASE_NAME = "DATABASE";
    static final String RECIPE_TABLE_NAME = "RECIPE";
    static final String INGREDIENT_TABLE_NAME = "INGREDIENT";
    static final String RELATION_TABLE_NAME = "RELATION";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db = sqLiteDatabase;
        init_Tables();
        initIngredientTable();
        initRecipeTable();
        initRelationTable();
    }

    private void init_Tables() {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + RECIPE_TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT UNIQUE, " +
                "details TEXT, " +
                "imagePath TEXT, " +
                "recipeType TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + INGREDIENT_TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT UNIQUE," +
                "imagePath TEXT UNIQUE, " +
                "remain INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + RELATION_TABLE_NAME + "(" +
                "recipe_id INTEGER," +
                "ingredient_id INTEGER," +
                "requirement INTEGER, " +
                "FOREIGN KEY(recipe_id) REFERENCES " + RECIPE_TABLE_NAME + "(id) ON DELETE CASCADE," +
                "FOREIGN KEY(ingredient_id) REFERENCES " + INGREDIENT_TABLE_NAME + "(id) ON DELETE CASCADE," +
                "PRIMARY KEY(recipe_id, ingredient_id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        return;
    }

    private void initRecipeTable() {
        ContentValues recipeContent = new ContentValues();
        recipeContent.put("name", "간장계란밥");
        recipeContent.put("details", "마구마구 넣고 마구마구 비벼 먹기");
        recipeContent.put("imagePath", "hello, world!");
        recipeContent.put("name", "한식");
        db.insert(RECIPE_TABLE_NAME, null, recipeContent);
    }

    private void initIngredientTable() {
        ContentValues ingredientsContent = new ContentValues();
        ingredientsContent.put("name", "밥");
        ingredientsContent.put("remain", 1000);
        ingredientsContent.put("imagePath", "Hello2, world!");
        db.insert(INGREDIENT_TABLE_NAME, null, ingredientsContent);
        ingredientsContent = new ContentValues();
        ingredientsContent.put("name", "간장");
        ingredientsContent.put("remain", 1000);
        ingredientsContent.put("imagePath", "Hello1, world!");
        db.insert(INGREDIENT_TABLE_NAME, null, ingredientsContent);
        ingredientsContent = new ContentValues();
        ingredientsContent.put("name", "계란");
        ingredientsContent.put("remain", 1000);
        ingredientsContent.put("imagePath", "Hello3, world!");
        db.insert(INGREDIENT_TABLE_NAME, null, ingredientsContent);
    }

    private void initRelationTable() {
        int recipeIdx = 1;

        ContentValues contentValues = new ContentValues();
        contentValues.put("recipe_id", recipeIdx);
        contentValues.put("ingredient_id", 1);
        contentValues.put("requirement", 500);
        db.insert(RELATION_TABLE_NAME, null, contentValues);
        contentValues = new ContentValues();
        contentValues.put("recipe_id", recipeIdx);
        contentValues.put("ingredient_id", 2);
        contentValues.put("requirement", 500);
        db.insert(RELATION_TABLE_NAME, null, contentValues);
        contentValues = new ContentValues();
        contentValues.put("recipe_id", recipeIdx);
        contentValues.put("ingredient_id", 3);
        contentValues.put("requirement", 500);
        db.insert(RELATION_TABLE_NAME, null, contentValues);

    }
}

