package com.example.nutritionapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nutritionapplication.NutritionAndroidApplication;
import com.example.nutritionapplication.adapter.MealArrayAdapter;
import com.example.nutritionapplication.databinding.ActivityMealBinding;
import com.example.nutritionapplication.dto.MealTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MealActivity extends AppCompatActivity {

    private NutritionAndroidApplication myApp;
    private ArrayList<MealTO> mealList;
    private MealArrayAdapter mealArrayAdapter;
    private ActivityMealBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();
        this.mealList = new ArrayList<MealTO>();
        this.mealArrayAdapter = new MealArrayAdapter(mealList, this);
        binding.slMealList.setAdapter(mealArrayAdapter);

        binding.slSwipeRefresh.setOnRefreshListener(() -> {
                    this.getMeals();
                    binding.slSwipeRefresh.setRefreshing(false);
                }
        );

        binding.fabNewMeal.setOnClickListener((View view) -> {
            this.goToCreateMeal();
        });

        this.getMeals();
    }

    @Override
    public void onBackPressed() {}

    public void getMeals() {
        Call<List<MealTO>> call = this.myApp.getMealService().getDailyMeals(10,04,2022,"peter@gmail.com");
        call.enqueue(new Callback<List<MealTO>>() {
            @Override
            public void onResponse(Call<List<MealTO>> call, Response<List<MealTO>> response) {
                if(response.isSuccessful()) {
                    ArrayList<MealTO> newMealList = (ArrayList<MealTO>) response.body();
                    mealList.clear();
                    mealList.addAll(newMealList);
                    System.out.println(mealList.get(0).getFoodEntries().toString());
                    mealArrayAdapter.notifyDataSetChanged();
                } else {
                    showToast("Communication error occured. " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<MealTO>> call, Throwable t) {
                showToast("Communication error occured. Try again!");
            }
        });

    }
    public void goToMeal(MealTO m) {
        Intent intent = new Intent(getApplicationContext(), FoodListActivity.class);
        Bundle b = new Bundle();
        b.putLong("id", m.getId());
        intent.putExtras(b);
        startActivity(intent);
    }

    public  void goToCreateMeal() {
        Intent intent = new Intent(getApplicationContext(), MealAddingActivity.class);
        startActivity(intent);
    }




    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
