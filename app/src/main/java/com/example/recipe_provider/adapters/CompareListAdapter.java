package com.example.recipe_provider.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipe_provider.R;
import com.example.recipe_provider.dto.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class CompareListAdapter extends NeedListAdapter{

    public CompareListAdapter(Context context, long idx) {
        super(context, idx);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_recipe, parent, false);
        }

        TextView nameItem = (TextView) convertView.findViewById(R.id.nameItem);
        TextView numItem = (TextView) convertView.findViewById(R.id.numItem);
        TextView numItemNeed = (TextView) convertView.findViewById(R.id.numItemNeed);

        Map.Entry<Ingredient, Integer> entry = mEntries.get(pos);
        Ingredient requireItem = entry.getKey();
        Integer requireAmount = entry.getValue();

        nameItem.setText(requireItem.getName());
        numItem.setText(requireAmount.toString());
        numItemNeed.setText(String.valueOf(requireItem.getRemain()));

        return convertView;
    }

}
