package com.example.recipe_provider.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.recipe_provider.R;
import com.example.recipe_provider.innerstorage.ImageStorage;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private final Context context;
    public static final String DATABASE_NAME = "DATABASE";
    private static final int DATABASE_VERSION = 1;
    static final String RECIPE_TABLE_NAME = "RECIPE";
    static final String INGREDIENT_TABLE_NAME = "INGREDIENT";
    static final String RELATION_TABLE_NAME = "RELATION";

    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context.getApplicationContext();
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
        insertRecipe("간장계란밥", "1. 계란을 후라이팬에 넣고 튀기듯이 익힌다.\n" +
                "2. 계란후라이가 완성되면 모든 재료를 한번에 넣고 비비면 완성.", R.drawable.eggbob, "한식");
        insertRecipe("신라면",
                "1. 냄비에 물을 넣는다.\n" +
                "2. 물이 끓을 때 까지 가열한 후 스프와 면을 넣는다.\n" +
                        "3. 3분간 더 끓인 뒤 완성.", R.drawable.recipe_ramyeon, "한식");
        insertRecipe("탕후루",
                "1. 설탕:물 5:1 비율 혼합물을 종이컵 안에 만든다.\n" +
                        "2. 종이컵을 전자레인지에 1분간 돌린다.\n" +
                        "3. 샤인머스켓을 젓가락에 꼽아 혼합물로 코팅한다.", R.drawable.tanghuru, "중식");
        insertRecipe("치즈버거",
                "1. 빵을 올린다.\n" +
                        "2. 체다치즈를 올린다. \n" +
                        "3. 패티를 올린다. \n" +
                        "4. 빵을 올린다.",
                R.drawable.cheeseburger,
                "양식");
    }

    private void initIngredientTable() {
        insertIngredient("밥", 1000, R.drawable.rice);
        insertIngredient("간장", 900, R.drawable.suisause);
        insertIngredient("계란", 800, R.drawable.egg);

        insertIngredient("물", 100000, R.drawable.water);
        insertIngredient("신라면 봉지", 1, R.drawable.sinramyeon);

        insertIngredient("파", 300, R.drawable.pa);
        insertIngredient("마늘", 300, R.drawable.maneul);
        insertIngredient("설탕", 500, R.drawable.sugar);
        insertIngredient("샤인머스켓", 0, R.drawable.muscat);
        insertIngredient("김치", 500, R.drawable.kimchi);

        insertIngredient("치즈", 500, R.drawable.cheese);
        insertIngredient("빵", 100, R.drawable.bbang);
        insertIngredient("패티", 200, R.drawable.patty);

    }

    private void initRelationTable() {
        insertRelation(1, 1, 500);
        insertRelation(1, 2, 500);
        insertRelation(1, 3, 500);

        insertRelation(2, 4, 500);
        insertRelation(2, 5, 1);

        insertRelation(3, 8, 200);
        insertRelation(3, 9, 500);

        insertRelation(4, 10, 2);
        insertRelation(4, 11, 2);
        insertRelation(4, 12, 1);
    }

    private void insertRecipe(String name, String details, int resourceId, String category) {
        ContentValues recipeContent = new ContentValues();
        recipeContent.put("name", name);
        recipeContent.put("details", details);
        recipeContent.put("imagePath",
                ImageStorage.saveToInternalStorage(
                        ImageStorage.getBitmapFromDrawable(context, resourceId)
                        , context
                )
        );
        recipeContent.put("recipeType", category);
        db.insert(RECIPE_TABLE_NAME, null, recipeContent);
    }

    private void insertIngredient(String name, int remain, int resourceId) {
        ContentValues ingreidientContent = new ContentValues();
        db.insert(INGREDIENT_TABLE_NAME, null, ingreidientContent);
        ingreidientContent = new ContentValues();
        ingreidientContent.put("name", name);
        ingreidientContent.put("remain", remain);
        ingreidientContent.put("imagePath",
                ImageStorage.saveToInternalStorage(
                        ImageStorage.getBitmapFromDrawable(context, resourceId)
                        , context
                )
        );
        db.insert(INGREDIENT_TABLE_NAME, null, ingreidientContent);
    }


    private void insertRelation(int recipeIdx, int ingredientIdx, int amount) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("recipe_id", recipeIdx);
        contentValues.put("ingredient_id", ingredientIdx);
        contentValues.put("requirement", amount);
        db.insert(RELATION_TABLE_NAME, null, contentValues);
    }
}

