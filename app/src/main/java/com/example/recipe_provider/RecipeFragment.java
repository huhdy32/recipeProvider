package com.example.recipe_provider;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 정렬 버튼 클릭 시 실행
                if(sortIndex)
                    sortButton.setText("이름순");
                else sortButton.setText("추천순");
                sortIndex = !sortIndex;
            }
        });

        ListView RecipeList = (ListView) rootView.findViewById(R.id.RecipeList);
        MainActivity.CustomAdapter adapter = new MainActivity.CustomAdapter();

        RecipeList.setAdapter(adapter);

        return rootView;
    }

}
