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
import com.example.recipe_provider.dto.RecipeEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeedListAdapter extends BaseAdapter {
        private long idx;
        private final RecipeRepository repository;
        private HashMap<Ingredient, Integer> mRequireItem;
        private List<Map.Entry<Ingredient, Integer>> mEntries;

        //해당 레시피의 필요 재료 해시맵을 불러오기
        public NeedListAdapter(Context context, long idx) {
            this.repository = new RecipeRepository(context);
            this.idx = idx;
            this.mRequireItem = repository.getRequireIngredient(idx);
            this.mEntries = new ArrayList<>(mRequireItem.entrySet());
        }

        //레시피 추가에 사용
        public NeedListAdapter(Context context,
                               HashMap<Ingredient, Integer> ingredientIntegerHashMap){
            this.repository = new RecipeRepository(context);
            this.mRequireItem = ingredientIntegerHashMap;
            this.mEntries = new ArrayList<>(mRequireItem.entrySet());
        }
        @Override
        public int getCount(){
            return mEntries.size();
        }
        @Override
        public long getItemId(int position){
            return position;
        }

        //선택된 아이템 Key 값 추출
        @Override
        public Ingredient getItem(int position){
            Map.Entry<Ingredient, Integer> entry = mEntries.get(position);
            return entry.getKey();
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

            Map.Entry<Ingredient, Integer> entry = mEntries.get(pos);
            Ingredient requireItem = entry.getKey();
            Integer requireAmount = entry.getValue();

            nameItem.setText(requireItem.getName());
            numItem.setText(requireAmount.toString());

            return convertView;
        }
}
