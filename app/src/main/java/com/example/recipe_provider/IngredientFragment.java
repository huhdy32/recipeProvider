package com.example.recipe_provider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IngredientFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_ingredient, container, false);

        ListView nameList = (ListView) rootView.findViewById(R.id.NameList);
        ListView numList = (ListView) rootView.findViewById(R.id.AmountList);

        return rootView;
    }

}
