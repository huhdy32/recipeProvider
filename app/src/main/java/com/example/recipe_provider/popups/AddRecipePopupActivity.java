package com.example.recipe_provider.popups;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_provider.R;
import com.example.recipe_provider.adapters.IngredientListAdapter;
import com.example.recipe_provider.adapters.NeedListAdapter;
import com.example.recipe_provider.database.IngredientRepository;
import com.example.recipe_provider.database.RecipeRepository;
import com.example.recipe_provider.dto.Ingredient;
import com.example.recipe_provider.dto.IngredientEntity;
import com.example.recipe_provider.dto.Recipe;

import java.util.HashMap;

public class AddRecipePopupActivity extends Activity {
    public Long ingredientId;
    public Integer amount_max;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_reciepe);

        RecipeRepository recipeRepository = new RecipeRepository(this);
        IngredientRepository ingredientRepository = new IngredientRepository(this);

        final HashMap<Ingredient, Integer> RequireItems = new HashMap<>();

        EditText itemName = findViewById(R.id.itemName);
        EditText itemType = findViewById(R.id.itemType);
        EditText itemDescribe = findViewById(R.id.itemDescribe);
        EditText itemAmount = findViewById(R.id.itemAmount);
        TextView searchBar = findViewById(R.id.searchBar);
        Button addButton = findViewById(R.id.addBtn);
        Button addIngredient = findViewById(R.id.ingredientAddBtn);

        ListView NeedList = (ListView) findViewById(R.id.needList);
        NeedListAdapter needListAdapter = new NeedListAdapter(this, RequireItems);

        NeedList.setAdapter(needListAdapter);

        NeedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                return;
            }
        });


        //리스트뷰 설정
        ListView IngredientList = (ListView) findViewById(R.id.IngredientList);
        IngredientListAdapter ingredientListAdapter = new IngredientListAdapter(this);

        IngredientList.setAdapter(ingredientListAdapter);

        IngredientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IngredientEntity selectedItem = ingredientListAdapter.getItem(position);
                ingredientId = ingredientListAdapter.getItemId(position);

                searchBar.setText(selectedItem.getName());
                amount_max = selectedItem.getRemain();
                itemAmount.setText(amount_max);
                ingredientId = ingredientListAdapter.getItemId(position);
            }
        });

        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountText = itemAmount.getText().toString();
                Integer amount = Integer.parseInt(amountText);
                if(amount > amount_max){
                    Toast.makeText(AddRecipePopupActivity.this, "재고가 부족합니다!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Ingredient toInput = ingredientRepository.get(ingredientId);
                RequireItems.put(toInput, amount);
                needListAdapter.notifyDataSetChanged();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(itemName.getText());
                String type = String.valueOf(itemType.getText());
                String Describe = String.valueOf(itemDescribe.getText());

                final Recipe recipe = new Recipe(name, Describe, "imagepath", RequireItems, type);
                recipeRepository.insert(recipe);
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