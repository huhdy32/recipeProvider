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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String DATABASE_NAME = "DATABASE";
    public static DatabaseHelper database ;
    // 각 프레그먼트 정의
    RecipeFragment recipefragment;
    IngredientFragment ingredientfragment;
    public static boolean fragmentIndex = true;
    Button swapbutton;

    // 리스트뷰 커스텀 어뎁터 정의
    public static class CustomAdapter extends BaseAdapter {
        private ArrayList<String> mName;
        private ArrayList<String> mNum;
        public CustomAdapter() {
            mName = new ArrayList<>();
            mNum = new ArrayList<>();

            // 예시 데이터 추가
            mName.add("사과");
            mNum.add("5");

            mName.add("바나나");
            mNum.add("3");

            mName.add("오렌지");
            mNum.add("8");
        }
        @Override
        public int getCount(){
            return mName.size();
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public Object getItem(int position){
            return mName.get(position);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();

            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item, parent, false);
            }

            TextView nameItem = (TextView) convertView.findViewById(R.id.nameItem);
            TextView numItem = (TextView) convertView.findViewById(R.id.numItem);

            // 아이템 내 위젯에 데이터 반영
            nameItem.setText(mName.get(pos));

            // 프래그먼트에 따라 수식언 추가
            String mod;
            if(fragmentIndex){
                mod = " %";
            } else mod = " g";

            numItem.setText(mNum.get(pos) + mod);

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 데이터 베이스 생성 코드
        database = new DatabaseHelper(this, DATABASE_NAME, null, 1);

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
            getSupportFragmentManager().beginTransaction().replace(R.id.action_container, ingredientfragment).commit();
            swapbutton.setText("레시피");

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.action_container, recipefragment).commit();
            swapbutton.setText("재료");
        }
    }
}
