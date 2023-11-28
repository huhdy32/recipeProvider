package com.example.recipe_provider;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.recipe_provider.adapters.RecipeListAdapter;
import com.example.recipe_provider.database.IngredientRepository;
import com.example.recipe_provider.dto.Ingredient;

public class DetailIngredientPopupActivity extends Activity {
    Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details_ingredient);
        IngredientRepository repository = new IngredientRepository(this);

        long itemId = getIntent().getLongExtra("ID", 0);

        // ID 기반 Ingredient 참조
        Ingredient Item = repository.get(itemId);

        TextView itemName = findViewById(R.id.itemName);
        TextView itemRemain = findViewById(R.id.itemRemain);
        // 임시적으로 textview로 선언함.
        TextView itemImage = findViewById(R.id.itemImage);

        itemName.setText(Item.getName());
        itemRemain.setText(Item.getRemain() + " g");
        itemImage.setText(Item.getImagePath());

        //삭제 버튼 리스너
        deleteButton = (Button) findViewById(R.id.deleteBtn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
