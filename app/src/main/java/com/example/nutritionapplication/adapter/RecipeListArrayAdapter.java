package com.example.nutritionapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nutritionapplication.FoodListActivity;
import com.example.nutritionapplication.R;
import com.example.nutritionapplication.dto.FoodEntryTO;
import com.example.nutritionapplication.dto.RezepteTO;


import java.util.Base64;
import java.util.List;

public class RecipeListArrayAdapter extends BaseAdapter {

    List<RezepteTO> rezepteList;
    private Context context;

    public RecipeListArrayAdapter(List<RezepteTO> list, Context context) {
        this.rezepteList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return rezepteList.size();
    }

    @Override
    public Object getItem(int pos) {
        return rezepteList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return rezepteList.get(pos).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_recipe_list, null);
        }

        TextView listItem = (TextView) view.findViewById(R.id.layoutTextName);
        listItem.setText(rezepteList.get(position).getName());
        TextView listItem2 = (TextView) view.findViewById(R.id.recipelayoutAuthor);
        listItem2.setText(rezepteList.get(position).getAuthor());
        ImageView image = (ImageView) view.findViewById(R.id.layoutImage);
        byte[] decodedString = Base64.getDecoder().decode(rezepteList.get(position).getImage());
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
        image.setImageBitmap(decodedByte);


        return view;
    }
}