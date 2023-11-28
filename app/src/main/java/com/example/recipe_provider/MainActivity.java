package com.example.recipe_provider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.recipe_provider.database.DatabaseHelper;
import com.example.recipe_provider.database.IngredientRepository;
import com.example.recipe_provider.database.RecipeRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static DatabaseHelper database ;
    // 각 프레그먼트 정의
    RecipeFragment recipefragment;
    IngredientFragment ingredientfragment;
    public static boolean fragmentIndex = true;
    Button swapbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 데이터 베이스 생성 코드
        database = new DatabaseHelper(this, DatabaseHelper.DATABASE_NAME, null, 1);

        //리포지토리 생성 코드
        swapbutton = findViewById(R.id.SwapButton);

        recipefragment = new RecipeFragment();
        ingredientfragment = new IngredientFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.action_container, recipefragment);

        // 프래그먼트 교체 버튼 리스너
        swapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentIndex = !fragmentIndex;
                FragmentChange();
            }
        });
    }

    //프래그먼트, 버튼 text 교체 함수
    public void FragmentChange() {
        if (fragmentIndex) {
            getSupportFragmentManager().beginTransaction().replace(R.id.action_container, recipefragment).commit();
            swapbutton.setText("재료");

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.action_container, ingredientfragment).commit();
            swapbutton.setText("레시피");
        }
    }
}
