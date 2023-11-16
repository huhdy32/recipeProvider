package com.example.recipe_provider.;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 각 프레그먼트 정의
    RecipeFragment recipefragment;
    IngredientFragment ingredientfragment;
    public boolean FragmentIndex = false;

    Button swapbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swapbutton = findViewById(R.id.SwapButton);

        recipefragment = (RecipeFragment) getSupportFragmentManager().findFragmentById(R.id.Fragment);
        ingredientfragment = new IngredientFragment();

        swapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentChange();
            }
        });
    }

    //프래그먼트, 버튼 text 교체 함수
    public void FragmentChange() {
        if (FragmentIndex) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment, ingredientfragment).commit();
            FragmentIndex = true;
            swapbutton.setText("레시피");

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment, recipefragment).commit();
            FragmentIndex = false;
            swapbutton.setText("재료");
        }
    }
}
