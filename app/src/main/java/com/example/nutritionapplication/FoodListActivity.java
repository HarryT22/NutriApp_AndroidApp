package com.example.nutritionapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nutritionapplication.adapter.FoodArrayAdapter;
import com.example.nutritionapplication.adapter.MealArrayAdapter;
import com.example.nutritionapplication.databinding.ActivityFoodentriesBinding;
import com.example.nutritionapplication.databinding.ActivityMealBinding;
import com.example.nutritionapplication.dto.FoodEntryTO;
import com.example.nutritionapplication.dto.MealTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.Toast;

public class FoodListActivity extends AppCompatActivity {
    private MealTO mealTO;
    private NutritionAndroidApplication myApp;
    private List<FoodEntryTO> foodEntryTOList;
    private FoodArrayAdapter foodArrayAdapter;
    private ActivityFoodentriesBinding binding;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityFoodentriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();
        this.foodEntryTOList = new ArrayList<FoodEntryTO>();
        this.foodArrayAdapter = new FoodArrayAdapter(foodEntryTOList, this);
        binding.slFoodEntries.setAdapter(foodArrayAdapter);
        Bundle b = getIntent().getExtras();
        this.id = b.getLong("id");

        binding.slSwipeRefresh.setOnRefreshListener(() -> {
                    this.getFoodEntries();
                    binding.slSwipeRefresh.setRefreshing(false);
                }
        );

        binding.fabNewFood.setOnClickListener((View view) -> {
            this.goToSearch();
        });

        this.getFoodEntries();
    }
    public void getFoodEntries() {
        Call<MealTO> call = this.myApp.getMealService().getMeal(1);
        call.enqueue(new Callback<MealTO>() {
            @Override
            public void onResponse(Call<MealTO> call, Response<MealTO> response) {
                if(response.isSuccessful()) {
                    MealTO meal = response.body();
                    foodEntryTOList.clear();
                    Set<FoodEntryTO> newList = meal.getFoodEntries();
                    foodEntryTOList.addAll(newList);
                    foodArrayAdapter.notifyDataSetChanged();
                } else {
                    showToast("Communication error occured. " + response.message());
                }
            }
            @Override
            public void onFailure(Call<MealTO> call, Throwable t) {
                showToast("Communication error occured. Try again!");
            }
        });
    }

    public void deleteFood(FoodEntryTO foodEntryTO) {
        Call<Void> call = this.myApp.getMealService().deleteFood(id, foodEntryTO.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    foodEntryTOList.remove(foodEntryTO);
                    foodArrayAdapter.notifyDataSetChanged();
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

    public void goToSearch(){
        Intent intent = new Intent(getApplicationContext(), FoodSearchActivity.class);
        Bundle b = new Bundle();
        b.putLong("id", this.id);
        intent.putExtras(b);
        startActivity(intent);
    }


    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
