package com.example.recipe_provider.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.recipe_provider.R;
import com.example.recipe_provider.database.IngredientRepository;
import com.example.recipe_provider.dto.Ingredient;
import com.example.recipe_provider.dto.IngredientEntity;
import com.example.recipe_provider.dto.RecipeEntity;

import java.util.Collections;
import java.util.List;

public class IngredientListAdapter extends BaseAdapter {
    private final IngredientRepository repository;
    private final List<IngredientEntity> mItems;

    public IngredientListAdapter(Context context) {
        this.repository = new IngredientRepository(context);
        this.mItems = repository.getAll();
    }

    public void sort(final boolean sortIndex) {
        if (sortIndex == false) {
            Collections.sort(mItems, ((o1, o2) -> o1.getName().compareTo(o2.getName())));
        } else {
            Collections.sort(mItems, (((o1, o2) -> (int) (o2.getRemain() - o1.getRemain()))));
        }
        this.notifyDataSetChanged();
    }

    public void search(final String text) {
        IngredientEntity entity = mItems.stream()
                .filter(ingredientEntity -> ingredientEntity.getName().contains(text))
                .findFirst()
                .orElse(null);
        if (entity == null) return;
        mItems.remove(entity);
        mItems.add(0, entity);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public long getItemId(final int position) {
        return mItems.get(position).getId();
    }

    @Override
    public IngredientEntity getItem(final int position) {
        return mItems.get(position);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView nameItem = (TextView) convertView.findViewById(R.id.nameItem);
        TextView numItem = (TextView) convertView.findViewById(R.id.numItem);

        final IngredientEntity item = mItems.get(pos);
        // 아이템 내 위젯에 데이터 반영
        nameItem.setText(item.getName());
        numItem.setText(item.getRemain() + " g");

        return convertView;
    }
}
