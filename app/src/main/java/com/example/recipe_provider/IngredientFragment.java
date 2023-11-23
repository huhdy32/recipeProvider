package com.example.recipe_provider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class IngredientFragment extends Fragment {

    public boolean sortIndex = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_ingredient, container, false);

        Button sortButton = (Button) rootView.findViewById(R.id.SortButton);
        Button addButton = (Button) rootView.findViewById(R.id.AddButton);

        //정렬 버튼 리스너
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sortIndex)
                    sortButton.setText("이름순");
                else sortButton.setText("잔량순");
                sortIndex = !sortIndex;
            }
        });

        // 재료 추가 버튼 리스너
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 재료 추가 팝업 호출
                Intent intent = new Intent(getContext(), AddIngredientPopupActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        ListView IngredientList = (ListView) rootView.findViewById(R.id.IngredientList);
        MainActivity.CustomAdapter adapter = new MainActivity.CustomAdapter();
        // 리스트 어뎁터 연결
        IngredientList.setAdapter(adapter);

        // 리스트 아이템 클릭 리스너
        IngredientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 재료 세부 사항 팝업 호출
                Intent intent = new Intent(getContext(), DetailIngredientPopupActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        return rootView;
    }
}
