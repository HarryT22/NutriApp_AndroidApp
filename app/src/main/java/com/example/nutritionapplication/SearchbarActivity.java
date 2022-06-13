package com.example.nutritionapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nutritionapplication.ListNormalActivity;
import com.example.nutritionapplication.NutritionAndroidApplication;
import com.example.nutritionapplication.databinding.ActivitySearchbarBinding;
import com.example.nutritionapplication.dto.RezepteTO;

import java.io.Serializable;
import java.util.List;

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
        this.myApp = (NutritionAndroidApplication) getApplication();

        binding.searchButton.setOnClickListener((View view) -> {
            if(binding.fructoseBox.isChecked()){
                this.fructose = true;
            }
            if(binding.lactoseBox.isChecked()){
                this.lactose = true;
            }
            if(binding.histamineBox.isChecked()){
                this.histamine = true;
            }
            if(binding.veganBox.isChecked()){
                this.isvegan = true;
            }
            if(binding.vegetarianBox.isChecked()){
                this.isvegetarian = true;
            }
            int minP = Integer.parseInt(binding.minPBox.getText().toString());
            int maxP = Integer.parseInt(binding.maxPBox.getText().toString());
            int minK = Integer.parseInt(binding.minKBox.getText().toString());
            int maxK = Integer.parseInt(binding.maxKBox.getText().toString());

            this.showList(binding.textinput.getText().toString(),isvegetarian,isvegan,fructose,lactose,histamine,minP,maxP,minK,maxK);
        });
    }

    private void showList(String rezeptName, boolean vegetarisch, boolean vegan, boolean fructose,
                          boolean lactose, boolean histamine, int minP, int maxP, int minK, int maxK) {
        Call<List<RezepteTO>> call = this.myApp.getRezepteService().listNormal
                ("Bearer " + this.myApp.getJwt(), rezeptName, fructose, lactose, histamine, vegan, vegetarisch, minK, maxK, minP, maxP);
        call.enqueue(new Callback<List<RezepteTO>>() {
            @Override
            public void onResponse(Call<List<RezepteTO>> call, Response<List<RezepteTO>> response) {
                if (response.isSuccessful()) {
                    List<RezepteTO> list = response.body();
                    Intent intent = new Intent(getApplicationContext(), ListNormalActivity.class);
                    intent.putExtra("LIST",(Serializable) list);
                    startActivity(intent);
                } else {
                    showToast("Communication error occurred. Try again!" + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<RezepteTO>> call, Throwable t) {
                showToast("Communication error occurred. Try again!");
            }
        })
        ;}



    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}