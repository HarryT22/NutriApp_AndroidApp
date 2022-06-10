/*package com.example.nutritionapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.nutritionapplication.MealActivity;
import com.example.nutritionapplication.R;
import com.example.nutritionapplication.dto.FoodDTO;
import com.example.nutritionapplication.dto.MealTO;
import com.example.nutritionapplication.dto.RezepteTO;

import java.util.ArrayList;
import java.util.List;

public class RecipeArrayAdapter extends ArrayAdapter<RezepteTO> {

    private Context gContext;
    private int gResource;


    public RecipeArrayAdapter(Context context, int resource, ArrayList<RezepteTO> objects) {
        super(context, resource, objects);
        this.gContext = context;
        this.gResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int id = getItem(position).getId();
        String name = getItem(position).getName();
        int arbeitszeit = getItem(position).getArbeitszeit();
        int kochzeit = getItem(position).getKochzeit();
        int gesamtzeit = getItem(position).getGesamtzeit();
        int portionen = getItem(position).getPortionen();
        String menueart = getItem(position).getMenueart();
        boolean isVegan = getItem(position).isVegan();
        boolean isVegetarisch = getItem(position).isVegetarisch();
        boolean histamine = getItem(position).isHistamine();
        boolean fructose = getItem(position).isFructose();
        boolean lactose = getItem(position).isLactose();
        int kalorien = getItem(position).getKalorien();
        int proteine = getItem(position).getProteine();
        String author = getItem(position).getAuthor();
        String image = getItem(position).getImage();
        List<FoodDTO> foods = getItem(position).getFoods();
        RezepteTO rezepteTO = new RezepteTO(id,name,foods,arbeitszeit,kochzeit,portionen,menueart,isVegan,
                isVegetarisch,fructose,lactose,histamine,gesamtzeit,kalorien,proteine,author,image);
        LayoutInflater inflater = LayoutInflater.from(gContext);
        convertView = inflater.inflate(gResource,parent,false);

        TextView rName = (TextView) convertView.findViewById(R.id.recipelistName);
        TextView rAz = (TextView) convertView.findViewById(R.id.recipelistWT);
        TextView rCt = (TextView) convertView.findViewById(R.id.recipelistCT);
        TextView rOt = (TextView) convertView.findViewById(R.id.recipelistOT);
        TextView rPortion = (TextView) convertView.findViewById(R.id.recipelistPortion);
        TextView rMenuart = (TextView) convertView.findViewById(R.id.recipelistMenutype);
        TextView rVegan = (TextView) convertView.findViewById(R.id.recipelistVegan);
        TextView rVegetar = (TextView) convertView.findViewById(R.id.recipelistVegetarian);
        TextView rHis = (TextView) convertView.findViewById(R.id.recipelistHistamine);
        TextView rLac = (TextView) convertView.findViewById(R.id.recipelistLactose);
        TextView rFruc = (TextView) convertView.findViewById(R.id.recipelistFructose);
        TextView rCal = (TextView) convertView.findViewById(R.id.recipelistCalories);
        TextView rPro = (TextView) convertView.findViewById(R.id.recipelistProtein);
        TextView rAuthor = (TextView) convertView.findViewById(R.id.recipelistAuthor);
        //Image view convert

        rName.setText(name);
        rAz.setText(arbeitszeit);
        rCt.setText(kochzeit);
        rOt.setText(gesamtzeit);
        rPortion.setText(portionen);
        rMenuart.setText(menueart);
        rVegan.setText(""+isVegan);
        rVegetar.setText(""+isVegetarisch);
        rHis.setText(""+histamine);
        rLac.setText(""+lactose);
        rFruc.setText(""+fructose);
        rCal.setText(kalorien);
        rPro.setText(proteine);
        rAuthor.setText(author);
        //Set image
        return convertView;
    }
}*/