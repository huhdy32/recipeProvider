package com.example.recipe_provider;

import static android.app.Activity.RESULT_OK;
import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.recipe_provider.adapters.IngredientListAdapter;
import com.example.recipe_provider.popups.AddIngredientPopupActivity;
import com.example.recipe_provider.popups.DetailIngredientPopupActivity;

public class IngredientFragment extends Fragment {

    public boolean sortIndex = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_ingredient, container, false);

        Button sortButton = (Button) rootView.findViewById(R.id.SortButton);
        Button AddButton = (Button) rootView.findViewById(R.id.AddButton);
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

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddIngredientPopupActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        ListView IngredientList = (ListView) rootView.findViewById(R.id.IngredientList);
        IngredientListAdapter adapter = new IngredientListAdapter(getContext());

        IngredientList.setAdapter(adapter);

        // 아이템 클릭 리스너
        IngredientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long selectedID = adapter.getItemId(position);
                Intent intent = new Intent(getActivity(), DetailIngredientPopupActivity.class);
                intent.putExtra("ID", selectedID);
                startActivityForResult(intent, 1);
            }
        });
        return rootView;

    }
}
