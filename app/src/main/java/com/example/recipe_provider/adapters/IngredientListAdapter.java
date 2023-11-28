package com.example.recipe_provider.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.recipe_provider.R;

import java.util.ArrayList;
public class IngredientListAdapter extends BaseAdapter {
        private ArrayList<String> mName;
        private ArrayList<String> mNum;
        public IngredientListAdapter() {
            mName = new ArrayList<>();
            mNum = new ArrayList<>();
        }
        @Override
        public int getCount(){
            return mName.size();
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public Object getItem(int position){
            return mName.get(position);
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
            nameItem.setText(mName.get(pos));

            numItem.setText(mNum.get(pos) + 'g');

            return convertView;
        }
}
