package com.example.nutritionapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.nutritionapplication.FoodSearchActivity;
import com.example.nutritionapplication.MealActivity;
import com.example.nutritionapplication.R;
import com.example.nutritionapplication.dto.FoodTO;
import com.example.nutritionapplication.dto.MealTO;

import java.util.ArrayList;


public class FoodSearchArrayAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<FoodTO> foodList;
    private Context context;

    public FoodSearchArrayAdapter(ArrayList<FoodTO> list, Context context) {
        this.foodList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int pos) {
        return foodList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return foodList.get(pos).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list_foodsearch, null);
        }

        TextView listItem = (TextView) view.findViewById(R.id.sl_ListFoodsearchItem);
        listItem.setText(foodList.get(position).toString());
        listItem.setOnClickListener((View v) -> {
            ((FoodSearchActivity) context).goToCreateEntry(foodList.get(position));
        });


        return view;
    }
}
