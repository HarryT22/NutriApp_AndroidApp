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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        displayRecipe();
    }
        public void displayRecipe(){
            Bundle b = getIntent().getExtras();
            TextView at = findViewById(R.id.displayrecipeWorkingtime);
            at.setText(String.valueOf(b.getInt("wt")));
            TextView ct = findViewById(R.id.displayrecipeCookingtime);
            ct.setText(String.valueOf(b.getInt("ct")));
            TextView ot = findViewById(R.id.displayrecipeOveralltime);
            ot.setText(String.valueOf(b.getInt("ot")));
            TextView p = findViewById(R.id.displayrecipePortionen);
            p.setText(String.valueOf(b.getInt("portionen")));
            TextView m = findViewById(R.id.displayrecipeMenutype);
            m.setText(b.getString("menu"));
            TextView v = findViewById(R.id.displayrecipeVegan);
            String vegan = ""+b.getBoolean("vegan");
            v.setText(vegan);
            TextView vg = findViewById(R.id.displayrecipeVegetarisch);
            String vegetarisch = ""+b.getBoolean("vegetarisch");
            vg.setText(vegetarisch);
            TextView f = findViewById(R.id.displayrecipeFructose);
            String fructose = ""+b.getBoolean("fructose");
            f.setText(fructose);
            TextView l = findViewById(R.id.displayrecipeLactose);
            String lactose = ""+b.getBoolean("lactose");
            l.setText(lactose);
            TextView h = findViewById(R.id.displayrecipeHistamine);
            String histamine = ""+b.getBoolean("histamine");
            h.setText(histamine);
            TextView c = findViewById(R.id.displayrecipeCalories);
            c.setText(String.valueOf(b.getInt("calories")));
            TextView pr = findViewById(R.id.displayrecipeProtein);
            pr.setText(String.valueOf(b.getInt("proteine")));
            TextView a = findViewById(R.id.displayrecipeAuthor);
            a.setText(b.getString("author"));
            TextView n = findViewById(R.id.displayrecipeName);
            n.setText(b.getString("rezeptName"));
            ImageView image = (ImageView) findViewById(R.id.displayrecipeImage);
            byte[] decodedString = Base64.getDecoder().decode(b.getString("image"));
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
            image.setImageBitmap(decodedByte);
        }
}