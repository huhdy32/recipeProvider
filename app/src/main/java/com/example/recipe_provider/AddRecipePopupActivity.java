package com.example.recipe_provider;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.recipe_provider.adapters.IngredientListAdapter;
import com.example.recipe_provider.database.IngredientRepository;
import com.example.recipe_provider.database.RecipeRepository;
import com.example.recipe_provider.dto.Ingredient;
import com.example.recipe_provider.dto.Recipe;

public class AddRecipePopupActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_reciepe);

        RecipeRepository recipeRepository = new RecipeRepository(this);
        IngredientRepository ingredientRepository = new IngredientRepository(this);

        EditText itemName = findViewById(R.id.itemName);
        EditText itemType = findViewById(R.id.itemType);
        EditText itemDescribe = findViewById(R.id.itemDescribe);
        EditText itemAmount = findViewById(R.id.itemAmount);
        Button addButton = findViewById(R.id.addBtn);

        //리스트뷰 설정
        ListView IngredientList = (ListView) findViewById(R.id.IngredientList);
        IngredientListAdapter adapter = new IngredientListAdapter(this);

        IngredientList.setAdapter(adapter);

        IngredientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long selectedID = adapter.getItemId(position);
                // 예시 해시맵 임의 생성, 수정 요망
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(itemName.getText());
                String type = String.valueOf(itemType.getText());
                String Describe = String.valueOf(itemDescribe.getText());
                String Amount = String.valueOf(itemAmount.getText());
                finish();
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