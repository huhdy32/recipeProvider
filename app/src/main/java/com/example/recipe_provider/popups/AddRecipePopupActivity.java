package com.example.recipe_provider.popups;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.recipe_provider.innerstorage.ImageStorage;

import java.io.IOException;
import java.util.HashMap;

public class AddRecipePopupActivity extends Activity {
    public long ingredientId;
    public Integer amount_max;
    public Integer PICK_IMAGE_REQUEST = 1;
    public String imagePath = null;
    ImageView itemImage;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_reciepe);

        RecipeRepository recipeRepository = new RecipeRepository(this);
        IngredientRepository ingredientRepository = new IngredientRepository(this);

        final HashMap<Ingredient, Integer> RequireItems = new HashMap<>();

        itemImage = findViewById(R.id.itemImage);
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

        itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        NeedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Ingredient target = needListAdapter.getItem(position);
                if (RequireItems.containsKey(target)) {
                    RequireItems.remove(target);
                    System.out.println("재료 제거됨!");
                } else {
                    System.out.println("오류가 발생했습니다.");
                }
                needListAdapter.notifyDataSetChanged();
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
                ingredientId = selectedItem.getId();

                searchBar.setText(selectedItem.getName());
                amount_max = selectedItem.getRemain();
                itemAmount.setText(String.valueOf(amount_max));
                Log.i("normal", "onItemClick used well");
            }
        });

        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountText = itemAmount.getText().toString();
                Integer amount = Integer.parseInt(amountText);
                Log.i("amount",  "amount = " + amount);
                Log.i("ingredientId", "Id = " + ingredientId);
                if(amount > amount_max){
                    Toast.makeText(AddRecipePopupActivity.this, "재고가 부족합니다!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 오류 발생?
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

                final Recipe recipe = new Recipe(name, Describe, imagePath, RequireItems, type);
                recipeRepository.insert(recipe);
                finish();
            }
        });

    }
    // 갤러리에서 사진을 가져온 후 저장
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                ImageStorage storage = new ImageStorage(); // contexts?
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                itemImage.setImageBitmap(bitmap);
                imagePath = storage.saveToInternalStorage(bitmap, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}