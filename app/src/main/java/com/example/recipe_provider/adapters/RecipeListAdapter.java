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
        private long idx;
        private final RecipeRepository repository;
        private List<RecipeEntity> mItems;
        private HashMap<Ingredient, Integer> mRequireItem;

        //idx가 -1 이면 레시피 리스트를 출력, 0 이상이면 해당 레시피의 필요 재료 해시맵을 출력한다.
        public RecipeListAdapter(Context context, long idx) {
            this.repository = new RecipeRepository(context);
            this.mItems = repository.getAll();
            this.idx = idx;
            if(idx > -1){
                this.mRequireItem = repository.getRequireIngredient(idx);
            }
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
            if(idx > -1){
                // mRequireItem의 키-값 쌍을 리스트로 변환
                List<Map.Entry<Ingredient, Integer>> entries = new ArrayList<>(mRequireItem.entrySet());

                Map.Entry<Ingredient, Integer> entry = entries.get(position);
                Ingredient requireItem = entry.getKey();
                Integer requireAmount = entry.getValue();

                nameItem.setText(requireItem.getName());
                numItem.setText(requireAmount.toString());
            }else{
                final RecipeEntity item = mItems.get(pos);
                nameItem.setText(item.getName());
                numItem.setText(item.getRate() + " %");
            }


            return convertView;
        }
}
