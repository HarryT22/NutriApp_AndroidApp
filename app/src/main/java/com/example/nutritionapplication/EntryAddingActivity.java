package com.example.nutritionapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutritionapplication.adapter.MealArrayAdapter;
import com.example.nutritionapplication.databinding.ActivityCreateFoodentryBinding;
import com.example.nutritionapplication.databinding.ActivityCreateMealBinding;
import com.example.nutritionapplication.databinding.ActivityMealBinding;
import com.example.nutritionapplication.dto.MealTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryAddingActivity extends AppCompatActivity {

    private NutritionAndroidApplication myApp;
    private long foodId;
    private long mealId;
    private String unitSize;
    private ActivityCreateFoodentryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityCreateFoodentryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();
        Bundle b = getIntent().getExtras();
        this.unitSize = b.getString("unitSize");
        if (unitSize.contains("ml")) {
            binding.entryQuantityQuestion.setText("Wie viel Milliliter möchtest du eintragen?");
            binding.unitSize.setText("ML");
        }
        else {
            binding.entryQuantityQuestion.setText("Wie viel Gram möchtest du eintragen?");
            binding.unitSize.setText("G");
        }
        this.foodId = b.getLong("foodId");
        this.mealId = b.getLong("mealId");


        binding.bnCreateEntry.setOnClickListener((View view) -> {
            String input = binding.inputQuantity.getText().toString();
            double inputDouble = Long.parseLong(input);
            int inputInt = (int) Math.ceil(inputDouble);
            this.createEntry(mealId, foodId, inputInt);

        });
    }


    public void createEntry(long mealId, long foodId, int quantity) {
        if(quantity <= 0) {
            showToast("Please enter valid quantity");
        } else {
            Call<Void> call = this.myApp.getMealService().addFood("Bearer " + this.myApp.getJwt(),mealId,foodId, quantity);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        showToast("Created Entry successfully");
                        Intent intent = new Intent(getApplicationContext(), FoodListActivity.class);
                        Bundle b = new Bundle();
                        b.putLong("id", mealId);
                        intent.putExtras(b);
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

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
