package com.example.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nutritionapplication.databinding.ActivityDeleteFoodFromRecipeBinding;
import com.example.nutritionapplication.dto.RezepteTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class deleteFoodFromRecipeActivity extends AppCompatActivity {

    RezepteTO rezepteTO;
    NutritionAndroidApplication myApp;
    ActivityDeleteFoodFromRecipeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityDeleteFoodFromRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.deleteFoodButton.setOnClickListener((View view) ->
                this.deleteFood(Integer.parseInt(binding.ffrRid.getText().toString()),Integer.parseInt(binding.ffrFid.getText().toString())));
    }

    public void deleteFood(int rId,int fId){
        Call<RezepteTO> call = this.myApp.getRezepteService().deleteFoodFromRezept("Bearer "+myApp.getJwt(),rId,fId);
        call.enqueue(new Callback<RezepteTO>() {
            @Override
            public void onResponse(Call<RezepteTO> call, Response<RezepteTO> response) {
                if(response.isSuccessful()){
                    showToast("Food List has been changed for recipe with ID: "+rId);

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