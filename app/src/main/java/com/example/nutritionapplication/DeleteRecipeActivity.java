package com.example.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nutritionapplication.databinding.ActivityDeleteRecipeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteRecipeActivity extends AppCompatActivity {
    ActivityDeleteRecipeBinding binding;
    NutritionAndroidApplication myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityDeleteRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();
        binding.deleteButton.setOnClickListener((View view) -> {
                    int id = Integer.parseInt(binding.deleteRecipieInput.getText().toString());
                    this.deleteRezept(id);
                }
        );
    }

    public void deleteRezept(int id){
        Call<Void> call = this.myApp.getRezepteService().deleteRezept(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    showToast("Recipe successfully deleted!");
                } else {
                    showToast("Communication error occurred. " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Communication error occurred. ");

            }
        });
    }
    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}