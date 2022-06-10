package com.example.nutritionapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutritionapplication.adapter.MealArrayAdapter;
import com.example.nutritionapplication.databinding.ActivityCreateMealBinding;
import com.example.nutritionapplication.databinding.ActivityMealBinding;
import com.example.nutritionapplication.dto.MealTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealAddingActivity extends AppCompatActivity {

    private NutritionAndroidApplication myApp;
    private String category;
    private ActivityCreateMealBinding binding;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityCreateMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();
        this.category = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Witz");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", null);
        this.dialog = builder.create();

        binding.bnGetJoke.setOnClickListener((View view) -> {
            this.getJoke("Programming");
        });

        binding.bnCreateMeal.setOnClickListener((View view) -> {
            this.createMeal(category);
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.sl_breakfast:
                if (checked)
                    this.category = "breakfast";
                break;
            case R.id.sl_lunch:
                if (checked)
                    this.category = "lunch";
                break;
            case R.id.sl_dinner:
                if (checked)
                    this.category = "dinner";
                break;
            case R.id.sl_snack:
                if (checked)
                    this.category = "snack";
                break;
        }
    }

    public void createMeal(String category) {
        if(category.equals("")) {
            showToast("Please choose a category");
        } else {
            Call<Void> call = this.myApp.getMealService().createMeal("Bearer " + this.myApp.getJwt(), 10,04,2022, category);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        showToast("Created Meal successfully");
                        Intent intent = new Intent(getApplicationContext(), MealActivity.class);
                        startActivity(intent);
                    } else {
                        showToast("Communication error occured. " + response.message());
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    showToast("Communication error occured. Try again!");
                }
            });
        }
    }

    public void getJoke(String category) {
        Call<String> call = this.myApp.getMealService().getJoke("Bearer " + this.myApp.getJwt(), category);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String joke = response.body().toString();
                    dialog.setMessage(joke);
                    dialog.show();
                } else {
                    showToast("Communication error occured. " + response.message());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showToast("Communication error occured. Try again!");
            }
        });
    }



    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
