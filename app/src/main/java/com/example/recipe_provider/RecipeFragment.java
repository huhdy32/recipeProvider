package com.example.recipe_provider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class RecipeFragment extends Fragment {

    public boolean sortIndex = true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recipe, container, false);

        Button sortButton = (Button) rootView.findViewById(R.id.SortButton);
        Button addButton = (Button) rootView.findViewById(R.id.AddButton);

        // 정렬 버튼 클릭 리스너
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sortIndex)
                    sortButton.setText("이름순");
                else sortButton.setText("추천순");
                sortIndex = !sortIndex;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메인 액티비티 컨텍스트 참조
                Intent intent = new Intent(getContext(), AddRecipePopupActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        ListView RecipeList = (ListView) rootView.findViewById(R.id.RecipeList);
        MainActivity.CustomAdapter adapter = new MainActivity.CustomAdapter();

        RecipeList.setAdapter(adapter);

        // 리스트 아이템 클릭 리스너
        RecipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 재료 세부 사항 팝업 호출
                Intent intent = new Intent(getContext(), DetailRecipePopupActivity.class);
                String selectedItem = adapter.getItem(position);

                intent.putExtra("itemkey", selectedItem);
                startActivityForResult(intent, 1);
            }
        });

        return rootView;
    }

}
