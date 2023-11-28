package com.example.recipe_provider;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_provider.adapters.RecipeListAdapter;
import com.example.recipe_provider.database.IngredientRepository;
import com.example.recipe_provider.database.RecipeRepository;
import com.example.recipe_provider.dto.Ingredient;
import com.example.recipe_provider.dto.Recipe;

public class DetailRecipePopupActivity extends Activity {
    Button deleteButton;
    Button makeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details_recipe);
        RecipeRepository repository = new RecipeRepository(this);

        long itemId = getIntent().getLongExtra("ID", 0);

        // ID 기반 Recipe 참조
        Recipe Item = repository.get(itemId);

        TextView itemName = findViewById(R.id.itemName);
        TextView itemDescribe = findViewById(R.id.itemDescribe);
        // 임시적으로 textview로 선언함.
        TextView itemImage = findViewById(R.id.itemImage);

        itemName.setText(Item.getName());
        itemDescribe.setText(Item.getDetails());
        itemImage.setText(Item.getImagePath());

        //리스트뷰 설정
        ListView needList = (ListView) findViewById(R.id.needList);

        RecipeListAdapter adapter = new RecipeListAdapter(this, itemId);

        needList.setAdapter(adapter);


        //삭제 버튼 리스너
        deleteButton = (Button) findViewById(R.id.deleteBtn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.delete(itemId);
                finish();
            }
        });

        //만들기 버튼 리스너
        makeButton = (Button) findViewById(R.id.makeBtn);
        makeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String msg;
                if(repository.create(Item))
                    msg = "재료 제거됨!";
                else
                    msg = "재료가 부족합니다.";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 바깥 레이어 클릭 시 닫힘 막기
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
