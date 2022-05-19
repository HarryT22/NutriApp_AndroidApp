package com.example.nutritionapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.nutritionapplication.MealActivity;
import com.example.nutritionapplication.R;
import com.example.nutritionapplication.dto.MealTO;

import java.util.ArrayList;
import java.util.List;


public class MealArrayAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<MealTO> mealList;
    private Context context;

    public MealArrayAdapter(ArrayList<MealTO> list, Context context) {
        this.mealList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mealList.size();
    }

    @Override
    public Object getItem(int pos) {
        return mealList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return mealList.get(pos).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list_meals, null);
        }

        TextView listItem = (TextView) view.findViewById(R.id.sl_ListMealItem);
        listItem.setText(mealList.get(position).toString());
        listItem.setOnClickListener((View v) -> {
            ((MealActivity) context).goToMeal(mealList.get(position));
        });


        return view;
    }
}
