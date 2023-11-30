package com.example.recipe_provider.popups;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_provider.R;
import com.example.recipe_provider.database.IngredientRepository;
import com.example.recipe_provider.database.RecipeRepository;
import com.example.recipe_provider.dto.Ingredient;

public class AddIngredientPopupActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_ingredient);

        IngredientRepository repository = new IngredientRepository(this);

        EditText itemName = findViewById(R.id.itemName);
        EditText itemAmount = findViewById(R.id.itemAmount);
        Button addButton = findViewById(R.id.addBtn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(itemName.getText());
                int Remain = Integer.parseInt(String.valueOf(itemAmount.getText()));
                Ingredient item = new Ingredient(0, name, Remain, null);
                if(repository.insert(item) >= 0){
                    finish();
                }else{
                    Toast.makeText(AddIngredientPopupActivity.this, "추가 실패!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
