package com.example.nutritionapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.nutritionapplication.FoodListActivity;
import com.example.nutritionapplication.R;
import com.example.nutritionapplication.dto.FoodEntryTO;

import java.util.List;

public class FoodArrayAdapter extends BaseAdapter implements ListAdapter {

    private List<FoodEntryTO> foodEntryTOList;
    private Context context;

    public FoodArrayAdapter(List<FoodEntryTO> list, Context context) {
        this.foodEntryTOList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return foodEntryTOList.size();
    }

    @Override
    public Object getItem(int pos) {
        return foodEntryTOList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return foodEntryTOList.get(pos).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list_food, null);
        }

        TextView listItem = (TextView) view.findViewById(R.id.sl_ListFoodItem);
        listItem.setText(foodEntryTOList.get(position).toString());

        ImageButton deleteButton = view.findViewById(R.id.bn_deleteFood);
        deleteButton.setOnClickListener((View v) -> {
            ((FoodListActivity) context).deleteFood(foodEntryTOList.get(position));
        });


        return view;
    }
}