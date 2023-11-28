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

import java.util.ArrayList;
import java.util.List;

public class IngredientListAdapter extends BaseAdapter {
        private final IngredientRepository repository;
        private List<IngredientEntity> mItems;
        private Ingredient mItem;
        public IngredientListAdapter(Context context) {
            this.repository = new IngredientRepository(context);
            this.mItems = repository.getAll();
        }
        @Override
        public int getCount(){
            return mItems.size();
        }
        @Override
        public long getItemId(int position){
            return mItems.get(position).getId();
        }
        @Override
        public IngredientEntity getItem(int position){
            return mItems.get(position);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();

            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item, parent, false);
            }

            TextView nameItem = (TextView) convertView.findViewById(R.id.nameItem);
            TextView numItem = (TextView) convertView.findViewById(R.id.numItem);

            final IngredientEntity item = mItems.get(pos);
            // 아이템 내 위젯에 데이터 반영
            nameItem.setText(item.getName());
            numItem.setText(item.getRemain() + 'g');

            return convertView;
        }
}
