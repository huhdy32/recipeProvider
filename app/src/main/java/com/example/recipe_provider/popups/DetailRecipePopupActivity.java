package com.example.recipe_provider.popups;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_provider.R;
import com.example.recipe_provider.adapters.NeedListAdapter;
import com.example.recipe_provider.adapters.RecipeListAdapter;
import com.example.recipe_provider.database.IngredientRepository;
import com.example.recipe_provider.database.RecipeRepository;
import com.example.recipe_provider.dto.Ingredient;
import com.example.recipe_provider.dto.Recipe;
import com.example.recipe_provider.innerstorage.ImageStorage;

public class DetailRecipePopupActivity extends Activity {
    Button deleteButton;
    Button makeButton;
    Button closeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details_recipe);
        RecipeRepository repository = new RecipeRepository(this);

        long itemId = -1;

        //컨텍스트 전달 예외 처리
        if(getIntent().hasExtra("ID")) {
            itemId = getIntent().getLongExtra("ID", -1);
        }else{
            Toast.makeText(this, "ID 값 없음!", Toast.LENGTH_SHORT).show();
            finish();
        }
        //ID 기반 Recipe 참조
        Recipe Item = repository.get(itemId);

        TextView itemName = findViewById(R.id.itemName);
        TextView itemDescribe = findViewById(R.id.itemDescribeText);
        //이미지뷰 선언
        ImageView itemImage = findViewById(R.id.itemImage);

        itemName.setText(Item.getName());
        itemDescribe.setText(Item.getDetails());

        //이미지 로드
        String imagePath = Item.getImagePath();
        itemImage.setImageBitmap(BitmapFactory.decodeFile(imagePath));
//        ImageStorage.loadImageFromStorage(imagePath, itemImage);

        //리스트뷰 설정
        ListView needList = (ListView) findViewById(R.id.needList);

        // 오류 검출 : itemId 검증
        Log.i("itemId", String.valueOf(itemId));
        NeedListAdapter adapter = new NeedListAdapter(this, itemId);

        needList.setAdapter(adapter);


        //삭제 버튼 리스너
        deleteButton = (Button) findViewById(R.id.deleteBtn);

        final long finalItemID = itemId;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.delete(finalItemID);
                finish();
            }
        });

        //닫기 버튼 리스너
        closeButton = (Button) findViewById(R.id.closeBtn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //만들기 버튼 리스너
        makeButton = (Button) findViewById(R.id.makeBtn);
        makeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String msg;

                // 이거 동작 안함, 수정요망
                if(repository.create(Item)) {
                    msg = "재료 제거됨!";
                    adapter.notifyDataSetChanged();
                }
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
