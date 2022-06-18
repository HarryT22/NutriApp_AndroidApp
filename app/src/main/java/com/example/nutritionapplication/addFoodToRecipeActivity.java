package com.example.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nutritionapplication.databinding.ActivityAddFoodToRecipeBinding;
import com.example.nutritionapplication.databinding.ActivityAddRecipeBinding;
import com.example.nutritionapplication.databinding.ActivityDeleteFoodFromRecipeBinding;
import com.example.nutritionapplication.dto.RezepteTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class addFoodToRecipeActivity extends AppCompatActivity {

    RezepteTO rezepteTO;
    NutritionAndroidApplication myApp;
    ActivityAddFoodToRecipeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityAddFoodToRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.foodAddSubmit.setOnClickListener((View view) ->
                this.addFood(Integer.parseInt(binding.addFoodId.getText().toString()),binding.foodNameInput.getText().toString(), Integer.parseInt(binding.calorieInput.getText().toString()),Integer.parseInt(binding.proteinInput.getText().toString()),
        binding.foodAmountInput.getText().toString()));
    }

    public void addFood(int id, String name, int calories, int protein, String menge){
        Call<RezepteTO> call = this.myApp.getRezepteService().addFood("Bearer "+myApp.getJwt(),id,name,calories,protein,menge);
        call.enqueue(new Callback<RezepteTO>() {
            @Override
            public void onResponse(Call<RezepteTO> call, Response<RezepteTO> response) {
                if(response.isSuccessful()){
                    showToast("Food List has been changed for recipe with ID: "+id +" recipename: " + name);

                }else{
                    showToast("Communication error occurred. Please try again!");
                }
            }

            @Override
            public void onFailure(Call<RezepteTO> call, Throwable t) {
                showToast("Communication error occured. Try again!");
            }
        });
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}