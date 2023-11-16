package com.example.recipe_provider;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recipe, container, false);

        ListView nameList = (ListView) rootView.findViewById(R.id.NameList);
        ListView ProgressList = (ListView) rootView.findViewById(R.id.ProgressList);

        return rootView;
    }

}
