package com.example.nutritionapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nutritionapplication.adapter.FoodArrayAdapter;
import com.example.nutritionapplication.adapter.FoodSearchArrayAdapter;
import com.example.nutritionapplication.adapter.MealArrayAdapter;
import com.example.nutritionapplication.databinding.ActivityFoodSearchBinding;
import com.example.nutritionapplication.databinding.ActivityFoodentriesBinding;
import com.example.nutritionapplication.databinding.ActivityMealBinding;
import com.example.nutritionapplication.dto.FoodEntryTO;
import com.example.nutritionapplication.dto.FoodTO;
import com.example.nutritionapplication.dto.MealTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

public class FoodSearchActivity extends AppCompatActivity {
    private NutritionAndroidApplication myApp;
    private ArrayList<FoodTO> foodList;
    private FoodSearchArrayAdapter foodsearchArrayAdapter;
    private ActivityFoodSearchBinding binding;
    private long id;
    private int day;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityFoodSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();
        this.foodList = new ArrayList<FoodTO>();
        this.foodsearchArrayAdapter = new FoodSearchArrayAdapter(foodList, this);
        binding.slFoodEntries.setAdapter(foodsearchArrayAdapter);
        Bundle b = getIntent().getExtras();
        this.id = b.getLong("id");
        this.month = b.getInt("month");
        this.year = b.getInt("year");
        this.day = b.getInt("day");

        binding.slSwipeRefresh.setOnRefreshListener(() -> {
                    binding.slSwipeRefresh.setRefreshing(false);
                }
        );

        binding.searchInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // do something on text submit
                getSearchResults(String.valueOf(binding.searchInput.getQuery()));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
    public void getSearchResults(String searchString) {
        if(searchString.length()<= 3) {
            showToast("Search have to include more than 3 letters");
        } else {
            Call<List<FoodTO>> call = this.myApp.getFoodService().getSearchResults("Bearer " + this.myApp.getJwt(), searchString);
            call.enqueue(new Callback<List<FoodTO>>() {
                @Override
                public void onResponse(Call<List<FoodTO>> call, Response<List<FoodTO>> response) {
                    if (response.isSuccessful()) {
                        List<FoodTO> newFoodList =response.body();
                        foodList.clear();
                        Collections.sort(newFoodList, (FoodTO f1, FoodTO f2) -> f1.getName().compareTo(f2.getName()));
                        foodList.addAll(newFoodList);
                        foodsearchArrayAdapter.notifyDataSetChanged();
                        binding.foodSearchHeading.setText("Suchergebnisse für \"" + searchString + "\"");
                    } else {
                        showToast("No food with this name was found. ");
                    }
                }
                @Override
                public void onFailure(Call<List<FoodTO>> call, Throwable t) {
                    showToast("Communication error occured. Try again!");
                }
            });
        }
    }

    public void goToCreateEntry(FoodTO food){
        Intent intent = new Intent(getApplicationContext(), EntryAddingActivity.class);
        Bundle b = new Bundle();
        b.putLong("mealId", this.id);
        b.putLong("foodId", food.getId());
        b.putInt("day", this.day);
        b.putInt("month", this.month);
        b.putInt("year", this.year);
        b.putString("unitSize", food.getUnitSize());
        intent.putExtras(b);
        startActivity(intent);
    }


    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}

