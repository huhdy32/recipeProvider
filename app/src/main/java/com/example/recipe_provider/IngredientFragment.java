package com.example.recipe_provider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.recipe_provider.adapters.IngredientListAdapter;
import com.example.recipe_provider.popups.AddIngredientPopupActivity;
import com.example.recipe_provider.popups.DetailIngredientPopupActivity;

public class IngredientFragment extends Fragment {

    public boolean sortIndex = true;
    IngredientListAdapter ingredientListAdapter;
    ListView ingredientView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_ingredient, container, false);

        Button sortButton = (Button) rootView.findViewById(R.id.SortButton);
        Button AddButton = (Button) rootView.findViewById(R.id.AddButton);
        EditText searchBox = rootView.findViewById(R.id.search_bar);
        Button searchBtn = rootView.findViewById(R.id.SearchButton);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchBox.getText() == null) return;
                ingredientListAdapter.search(searchBox.getText().toString());
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 정렬 버튼 클릭 시 실행
                ingredientListAdapter.sort(sortIndex);
                if(sortIndex)
                    sortButton.setText("이름순");
                else sortButton.setText("잔량순");
                sortIndex = !sortIndex;
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddIngredientPopupActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        ingredientView = (ListView) rootView.findViewById(R.id.IngredientList);
        ingredientListAdapter = new IngredientListAdapter(getContext());

        ingredientView.setAdapter(ingredientListAdapter);

        // 아이템 클릭 리스너
        ingredientView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long selectedID = ingredientListAdapter.getItemId(position);
                Intent intent = new Intent(getActivity(), DetailIngredientPopupActivity.class);
                intent.putExtra("ID", selectedID);
                startActivityForResult(intent, 1);
            }
        });
        return rootView;

    }

    @Override
    public void onResume() {
        ingredientListAdapter = new IngredientListAdapter(getContext());
        ingredientView.setAdapter(ingredientListAdapter);
        super.onResume();
    }
}
