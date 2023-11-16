package com.example.recipe_provider.;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RecipeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_view, container, false);

        ListView nameList = (ListView) rootView.findViewById(R.id.nameList);
        ListView numList = (ListView) rootView.findViewById(R.id.numList);

        //ListView와 MainActivity의 리스트를 연결하기 위한 어뎁터 정의
        ArrayAdapter nameAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                ((MainActivity) requireActivity()).nameList);
        ArrayAdapter numAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                ((MainActivity) requireActivity()).numList);

        //각 리스트뷰를 어뎁터에 설정
        nameList.setAdapter(nameAdapter);
        numList.setAdapter(numAdapter);

        return rootView;
    }

}
