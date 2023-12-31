package com.example.recipe_provider.popups;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_provider.R;
import com.example.recipe_provider.database.IngredientRepository;
import com.example.recipe_provider.dto.Ingredient;

public class DetailIngredientPopupActivity extends Activity {
    Button deleteButton;
    Button updateButton;
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
        EditText itemRemain = findViewById(R.id.itemRemain);
        ImageView itemImage = findViewById(R.id.itemImage);

        itemName.setText(Item.getName());
        itemRemain.setText(String.valueOf(Item.getRemain()));

        String imagePath = Item.getImagePath();
        itemImage.setImageBitmap(BitmapFactory.decodeFile(imagePath));
//        ImageStorage.loadImageFromStorage(imagePath, itemImage);

        long finalItemId = itemId;

        updateButton = (Button) findViewById(R.id.updateBtn);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer amount = Integer.parseInt(itemRemain.getText().toString());
                repository.updateRemain(finalItemId, amount);
                finish();
            }
        });

        //삭제 버튼 리스너
        deleteButton = (Button) findViewById(R.id.deleteBtn);
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
