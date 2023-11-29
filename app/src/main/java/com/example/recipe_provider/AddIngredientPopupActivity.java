package com.example.recipe_provider;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recipe_provider.database.IngredientRepository;
import com.example.recipe_provider.database.RecipeRepository;
import com.example.recipe_provider.dto.Ingredient;

public class AddIngredientPopupActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_reciepe);

        RecipeRepository repository = new RecipeRepository(this);

        EditText itemName = findViewById(R.id.itemName);
        EditText itemType = findViewById(R.id.itemType);
        EditText itemDescribe = findViewById(R.id.itemDescribe);
        EditText itemAmount = findViewById(R.id.itemAmount);
        Button addButton = findViewById(R.id.addBtn);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
