package com.example.recipe_provider.popups;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.recipe_provider.database.RecipeRepository;
import com.example.recipe_provider.dto.Ingredient;
import com.example.recipe_provider.innerstorage.ImageStorage;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class AddIngredientPopupActivity extends Activity {
    static Integer PICK_IMAGE_REQUEST = 1;
    public String imagePath = null;
    ImageView imageUpload;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_ingredient);

        IngredientRepository repository = new IngredientRepository(this);

        TextInputEditText itemName = findViewById(R.id.itemName);
        TextInputEditText itemAmount = findViewById(R.id.itemAmount);
        Button addButton = findViewById(R.id.addBtn);
        imageUpload = findViewById(R.id.itemImage);

        //이미지 업로드
        imageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(itemName.getText());
                int Remain = Integer.parseInt(String.valueOf(itemAmount.getText()));
                Ingredient item = new Ingredient(0, name, Remain, imagePath);
                if(repository.insert(item) >= 0){
                    finish();
                }else{
                    Toast.makeText(AddIngredientPopupActivity.this, "추가 실패!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    // 갤러리에서 사진을 가져온 후 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
//                ImageStorage storage = new ImageStorage(); // contexts?
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageUpload.setImageBitmap(bitmap);
                imagePath = ImageStorage.saveToInternalStorage(bitmap, this);
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
