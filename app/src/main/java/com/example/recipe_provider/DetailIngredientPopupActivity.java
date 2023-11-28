package com.example.recipe_provider;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.recipe_provider.database.IngredientRepository;
import com.example.recipe_provider.dto.Ingredient;

public class DetailIngredientPopupActivity extends Activity {
    Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_ingredient2);
        IngredientRepository repository = new IngredientRepository(this);

        long itemId = getIntent().getLongExtra("ID", 0);
        Ingredient Item = repository.get(itemId);




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
