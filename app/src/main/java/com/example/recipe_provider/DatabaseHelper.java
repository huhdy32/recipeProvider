package com.example.recipe_provider;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
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
        initRecipeTable();
        initIngredientTable();
        initRelationTable();
    }
    private void init_Tables() {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + RECIPE_TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT UNIQUE);");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + INGREDIENT_TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT UNIQUE," +
                "remain INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + RELATION_TABLE_NAME + "(" +
                "recipe_id INTEGER," +
                "ingredient_id INTEGER," +
                "requirement INTEGER, " +
                "FOREIGN KEY(recipe_id) REFERENCES " + RECIPE_TABLE_NAME + "(id)," +
                "FOREIGN KEY(ingredient_id) REFERENCES " + INGREDIENT_TABLE_NAME +"(id)," +
                "PRIMARY KEY(recipe_id, ingredient_id));");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        return;
    }

    private void initRecipeTable() {
        db = getWritableDatabase();

        ContentValues recipeContent = new ContentValues();
        recipeContent.put("recipe_name", "간장계란밥");
        db.insert(RECIPE_TABLE_NAME, null, recipeContent);

        ContentValues ingredientsContent = new ContentValues();
        ingredientsContent.put("name", "밥");
        ingredientsContent.put("remain", 1000);
        db.insert(INGREDIENT_TABLE_NAME, null, ingredientsContent);
        ingredientsContent = new ContentValues();
        ingredientsContent.put("name", "간장");
        ingredientsContent.put("remain", 1000);
        db.insert(INGREDIENT_TABLE_NAME, null, ingredientsContent);
        ingredientsContent = new ContentValues();
        ingredientsContent.put("name", "계란");
        ingredientsContent.put("remain", 1000);
        db.insert(INGREDIENT_TABLE_NAME, null, ingredientsContent);

        db.close();
    }

    private void initIngredientTable() {
        db = getWritableDatabase();
        ContentValues ingredientsContent = new ContentValues();

        ingredientsContent.put("name", "밥");
        ingredientsContent.put("remain", 1000);
        db.insert(INGREDIENT_TABLE_NAME, null, ingredientsContent);
        ingredientsContent = new ContentValues();
        ingredientsContent.put("name", "간장");
        ingredientsContent.put("remain", 1000);
        db.insert(INGREDIENT_TABLE_NAME, null, ingredientsContent);
        ingredientsContent = new ContentValues();
        ingredientsContent.put("name", "계란");
        ingredientsContent.put("remain", 1000);
        db.insert(INGREDIENT_TABLE_NAME, null, ingredientsContent);

        db.close();
    }

    private void initRelationTable() {
        String[] ingredientNames = {"밥", "간장", "계란"};
        int recipeIdx;
        int ingredientIdx;
        db = getReadableDatabase();
        Cursor cursor = db.query(
                RECIPE_TABLE_NAME,
                new String[]{"id"},
                "name = 간장계란밥",
                null,
                null,
                null,
                null);
        recipeIdx = cursor.getInt(0);

        for (String ingredientName : ingredientNames) {
            db = getReadableDatabase();
            cursor = db.query(
                    INGREDIENT_TABLE_NAME,
                    new String[]{"id"},
                    "name = " + ingredientName,
                    null,
                    null,
                    null,
                    null);
            ingredientIdx = cursor.getInt(0);

            ContentValues contentValues = new ContentValues();
            contentValues.put("recipe_id", recipeIdx);
            contentValues.put("ingredient_id", ingredientIdx);
            contentValues.put("requirement", 500);
            db = getWritableDatabase();
            db.insert(RELATION_TABLE_NAME, null, contentValues);
        }
        cursor.close();
        db.close();
    }
}
