package com.example.recipe_provider.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.recipe_provider.R;
import com.example.recipe_provider.database.RecipeRepository;
import com.example.recipe_provider.dto.Ingredient;
import com.example.recipe_provider.dto.Recipe;
import com.example.recipe_provider.dto.RecipeEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeListAdapter extends BaseAdapter {
        private final RecipeRepository repository;
        private List<RecipeEntity> mItems;

        //레시피 리스트 불러오기.
        public RecipeListAdapter(Context context) {
            this.repository = new RecipeRepository(context);
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
        public Object getItem(int position){
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

            // 아이템 내 위젯에 데이터 반영
            final RecipeEntity item = mItems.get(pos);
            nameItem.setText(item.getName());
            numItem.setText(item.getRate() + " %");

            return convertView;
        }
}
