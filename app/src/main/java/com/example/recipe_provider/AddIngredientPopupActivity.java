package com.example.recipe_provider;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class AddIngredientPopupActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details_ingredient);

    }
}
