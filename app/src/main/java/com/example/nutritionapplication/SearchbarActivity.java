package com.example.nutritionapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nutritionapplication.ListNormalActivity;
import com.example.nutritionapplication.NutritionAndroidApplication;
import com.example.nutritionapplication.adapter.RecipeListArrayAdapter;
import com.example.nutritionapplication.databinding.ActivitySearchbarBinding;
import com.example.nutritionapplication.dto.RezepteTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchbarActivity extends AppCompatActivity {

    private NutritionAndroidApplication myApp;
    private ActivitySearchbarBinding binding;
    boolean fructose = false;
    boolean lactose = false;
    boolean histamine = false;
    boolean isvegan = false;
    boolean isvegetarian = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySearchbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.myApp = (NutritionAndroidApplication) getApplication();
        binding.searchButton.setOnClickListener((View view) -> {
            this.showList();
        });
    }

    private void showList() {
        if (binding.fructoseBox.isChecked()) {
            this.fructose = true;
        }
        if (binding.lactoseBox.isChecked()) {
            this.lactose = true;
        }
        if (binding.histamineBox.isChecked()) {
            this.histamine = true;
        }
        if (binding.veganBox.isChecked()) {
            this.isvegan = true;
        }
        if (binding.vegetarianBox.isChecked()) {
            this.isvegetarian = true;
        }
        int minP = Integer.parseInt(binding.minPBox.getText().toString());
        int maxP = Integer.parseInt(binding.maxPBox.getText().toString());
        int minK = Integer.parseInt(binding.minKBox.getText().toString());
        int maxK = Integer.parseInt(binding.maxKBox.getText().toString());
        String rezeptName = binding.textinput.getText().toString();
        Intent i = new Intent(getApplicationContext(),ListNormalActivity.class);
        Bundle b = new Bundle();
        b.putInt("minK",minK);
        b.putInt("maxK",maxK);
        b.putInt("minP",minP);
        b.putInt("maxP",maxP);
        b.putString("rezeptName",rezeptName);
        b.putBoolean("fructose",fructose);
        b.putBoolean("lactose",lactose);
        b.putBoolean("histamine",histamine);
        b.putBoolean("vegan",isvegan);
        b.putBoolean("vegetarisch",isvegetarian);
        i.putExtras(b);
        startActivity(i);
    }



    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}