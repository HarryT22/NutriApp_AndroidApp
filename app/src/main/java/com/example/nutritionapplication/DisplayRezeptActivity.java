package com.example.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nutritionapplication.databinding.ActivityDisplayRezeptBinding;
import com.example.nutritionapplication.dto.RezepteTO;

import java.util.Base64;

public class DisplayRezeptActivity extends AppCompatActivity {

    ActivityDisplayRezeptBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityDisplayRezeptBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i = getIntent();
        RezepteTO rezepte  = (RezepteTO) i.getSerializableExtra("Rezept");
        TextView at = findViewById(R.id.displayrecipeWorkingtime);
        at.setText(rezepte.getArbeitszeit());
        TextView ct = findViewById(R.id.displayrecipeCookingtime);
        ct.setText(rezepte.getKochzeit());
        TextView ot = findViewById(R.id.displayrecipeOveralltime);
        ot.setText(rezepte.getGesamtzeit());
        TextView p = findViewById(R.id.displayrecipePortionen);
        p.setText(rezepte.getPortionen());
        TextView m = findViewById(R.id.displayrecipeMenutype);
        m.setText(rezepte.getMenueart());
        TextView v = findViewById(R.id.displayrecipeVegan);
        v.setText(""+rezepte.isVegan());
        TextView vg = findViewById(R.id.displayrecipeVegetarisch);
        vg.setText(""+rezepte.isVegetarisch());
        TextView f = findViewById(R.id.displayrecipeFructose);
        f.setText(""+rezepte.isFructose());
        TextView l = findViewById(R.id.displayrecipeLactose);
        l.setText(""+rezepte.isLactose());
        TextView h = findViewById(R.id.displayrecipeHistamine);
        h.setText(""+rezepte.isHistamine());
        TextView c = findViewById(R.id.displayrecipeCalories);
        c.setText(rezepte.getKalorien());
        TextView pr = findViewById(R.id.displayrecipeProtein);
        pr.setText(rezepte.getProteine());
        TextView a = findViewById(R.id.displayrecipeAuthor);
        a.setText(rezepte.getAuthor());
        ImageView image = (ImageView) findViewById(R.id.displayrecipeImage);
        byte[] decodedString = Base64.getDecoder().decode(rezepte.getImage());
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
        image.setImageBitmap(decodedByte);
    }

}