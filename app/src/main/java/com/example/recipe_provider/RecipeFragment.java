package com.example.recipe_provider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.recipe_provider.adapters.RecipeListAdapter;
import com.example.recipe_provider.popups.AddRecipePopupActivity;
import com.example.recipe_provider.popups.DetailRecipePopupActivity;

public class RecipeFragment extends Fragment {

    public boolean sortIndex = true;
    RecipeListAdapter recipeListAdapter;
    ListView recipeView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recipe, container, false);

        SearchView searchView = rootView.findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query == null) return true;
                recipeListAdapter.search(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                recipeListAdapter.search(newText);
                return true;
            }
        });

        Button sortButton = (Button) rootView.findViewById(R.id.SortButton);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 정렬 버튼 클릭 시 실행
                recipeListAdapter.sort(sortIndex);
                if(sortIndex)
                    sortButton.setText("이름순");
                else sortButton.setText("추천순");
                sortIndex = !sortIndex;
            }
        });

        Button addButton = (Button) rootView.findViewById(R.id.AddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddRecipePopupActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        recipeView = (ListView) rootView.findViewById(R.id.RecipeList);
        recipeListAdapter = new RecipeListAdapter(getContext());

        recipeView.setAdapter(recipeListAdapter);

        // 아이템 클릭 리스너
        recipeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long selectedID = recipeListAdapter.getItemId(position);
                Intent intent = new Intent(getActivity(), DetailRecipePopupActivity.class);
                intent.putExtra("ID", selectedID);
                startActivityForResult(intent, 1);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        recipeListAdapter = new RecipeListAdapter(getContext());
        recipeView.setAdapter(recipeListAdapter);
        super.onResume();
    }
}
