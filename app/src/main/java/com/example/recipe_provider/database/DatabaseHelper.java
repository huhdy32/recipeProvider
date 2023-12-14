package com.example.recipe_provider.database;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.recipe_provider.dto.Ingredient;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public static final String DATABASE_NAME = "DATABASE";
    private static final int DATABASE_VERSION = 1;
    static final String RECIPE_TABLE_NAME = "RECIPE";
    static final String INGREDIENT_TABLE_NAME = "INGREDIENT";
    static final String RELATION_TABLE_NAME = "RELATION";

    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
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
                "imagePath TEXT, " +
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
        recipeContent.put("recipeType", "한식");
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
        ingredientsContent.put("remain", 900);
        ingredientsContent.put("imagePath", "Hello1, world!");
        db.insert(INGREDIENT_TABLE_NAME, null, ingredientsContent);
        ingredientsContent = new ContentValues();
        ingredientsContent.put("name", "계란");
        ingredientsContent.put("remain", 800);
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

