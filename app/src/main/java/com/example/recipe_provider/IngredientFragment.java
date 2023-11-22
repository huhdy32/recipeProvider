package com.example.recipe_provider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 정렬 버튼 클릭 시 실행
                if(sortIndex)
                    sortButton.setText("이름순");
                else sortButton.setText("잔량순");
                sortIndex = !sortIndex;
            }
        });

        ListView IngredientList = (ListView) rootView.findViewById(R.id.IngredientList);
        MainActivity.CustomAdapter adapter = new MainActivity.CustomAdapter();

        IngredientList.setAdapter(adapter);

        return rootView;
    }
}
