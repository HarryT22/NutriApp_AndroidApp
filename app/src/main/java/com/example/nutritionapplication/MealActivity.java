package com.example.nutritionapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.nutritionapplication.NutritionAndroidApplication;
import com.example.nutritionapplication.adapter.MealArrayAdapter;
import com.example.nutritionapplication.databinding.ActivityMealBinding;
import com.example.nutritionapplication.dto.MealTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();
        this.mealList = new ArrayList<MealTO>();
        this.mealArrayAdapter = new MealArrayAdapter(mealList, this);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        binding.slMealList.setAdapter(mealArrayAdapter);

        binding.slSwipeRefresh.setOnRefreshListener(() -> {
                    this.getMeals();
                    binding.slSwipeRefresh.setRefreshing(false);
                }
        );
        binding.fabNewMeal.setOnClickListener((View view) -> {
            this.goToCreateMeal();
        });

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker view, int _year,
                                          int monthOfYear, int dayOfMonth) {
                        binding.headingMeals.setText("Meals " + (dayOfMonth) + "." + (monthOfYear+1) + "." + _year);
                        day = dayOfMonth;
                        month = monthOfYear+1;
                        year = _year;
                        getMeals();
                    }
                }, year, month, day);
        if(getIntent().getExtras() == null){
             datePickerDialog.show();
         }
        else{
            Bundle b = getIntent().getExtras();
            this.day = b.getInt("day");
            this.month = b.getInt("month");
            this.year = b.getInt("year");
            binding.headingMeals.setText("Meals " + day + "." + month + "." + year);
            getMeals();
        }
        binding.bnChooseDate.setOnClickListener((View view) -> {
            datePickerDialog.show();
        });
    }

    @Override
    public void onBackPressed() {}

    public void getMeals() {
        Call<List<MealTO>> call = this.myApp.getMealService().getDailyMeals("Bearer " + this.myApp.getJwt(), day,month,year);
        call.enqueue(new Callback<List<MealTO>>() {
            @Override
            public void onResponse(Call<List<MealTO>> call, Response<List<MealTO>> response) {
                if(response.isSuccessful()) {
                    ArrayList<MealTO> newMealList = (ArrayList<MealTO>) response.body();
                    mealList.clear();
                    mealList.addAll(newMealList);
                    mealArrayAdapter.notifyDataSetChanged();
                } else {
                    mealList.clear();
                    mealArrayAdapter.notifyDataSetChanged();
                    showToast("No meal for this date found. ");
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
        b.putInt("day", this.day);
        b.putInt("month", this.month);
        b.putInt("year", this.year);
        intent.putExtras(b);
        startActivity(intent);
    }

    public  void goToCreateMeal() {
        Intent intent = new Intent(getApplicationContext(), MealAddingActivity.class);
        Bundle b = new Bundle();
        b.putInt("day", this.day);
        b.putInt("month", this.month);
        b.putInt("year", this.year);
        intent.putExtras(b);
        startActivity(intent);
    }




    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
