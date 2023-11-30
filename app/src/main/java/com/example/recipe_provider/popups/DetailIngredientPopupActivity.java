package com.example.recipe_provider.popups;

import static android.app.ProgressDialog.show;
import static java.security.AccessController.getContext;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_provider.MainActivity;
import com.example.recipe_provider.R;
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
        long itemId = -1;

        //컨텍스트 전달 예외 처리
        if(getIntent().hasExtra("ID")) {
            itemId = getIntent().getLongExtra("ID", -1);
        }else{
            Toast.makeText(this, "ID 값 없음!", Toast.LENGTH_SHORT).show();
            finish();
        }

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
        long finalItemId = itemId;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repository.delete(finalItemId) >= 0){
                    finish();
                }else{
                    Toast.makeText(DetailIngredientPopupActivity.this, "삭제 실패!", Toast.LENGTH_SHORT).show();
                }
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
