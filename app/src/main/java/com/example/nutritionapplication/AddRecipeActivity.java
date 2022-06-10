package com.example.nutritionapplication;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nutritionapplication.databinding.ActivityAddRecipeBinding;
import com.example.nutritionapplication.dto.FoodDTO;
import com.example.nutritionapplication.dto.RezepteTO;
import com.example.nutritionapplication.service.RezepteService;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class AddRecipeActivity extends AppCompatActivity {

    private NutritionAndroidApplication myApp;
    private RezepteTO rezepteTO;
    ActivityAddRecipeBinding binding;
    boolean fructose = false;
    boolean lactose = false;
    boolean histamine = false;
    boolean isvegan = false;
    boolean isvegetarian = false;
    MultipartBody image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityAddRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();

        binding.submitRecipie.setOnClickListener((View view) ->{

            if(binding.fructoseSubmitBox.isChecked()){
                this.fructose = true;
            }
            if(binding.lactoseSubmitBox.isChecked()){
                this.lactose = true;
            }
            if(binding.histamineSubmitBox.isChecked()){
                this.histamine = true;
            }
            if(binding.veganSubmitBox.isChecked()){
                this.isvegan = true;
            }
            if(binding.vegetarianSubmitBox.isChecked()){
                this.isvegetarian = true;
            }

            int portions = Integer.parseInt(binding.portionsSubmit.getText().toString());
            int cookingtime = Integer.parseInt(binding.cookingtimeSubmit.getText().toString());
            int workingtime = Integer.parseInt(binding.workingtimeSubmit.getText().toString());

            this.saveRezept(binding.recipenameSubmit.getText().toString(),workingtime,cookingtime,
                    portions,binding.menueTypeSubmit.getText().toString(),isvegan,isvegetarian,fructose,lactose,histamine,image);

        });
    }

    public void saveRezept(String name, int arbeitszeit, int kochzeit, int portionen, String menueart, boolean isVegan, boolean isVegetarisch,
                           boolean fructose, boolean lactose, boolean histamine, MultipartBody image){
        if(name.equals("")){
            showToast("No empty recipe name allowed");
        }else{
            Call<RezepteTO> call = this.myApp.getRezepteService().saveRezept
                    ("Bearer "+ this.myApp.getJwt(), name,arbeitszeit,kochzeit,portionen,menueart,isVegan,isVegetarisch,histamine,lactose,fructose,image);
            call.enqueue(new Callback<RezepteTO>() {
                @Override
                public void onResponse(Call<RezepteTO> call, Response<RezepteTO> response) {
                    if(response.isSuccessful()){
                        rezepteTO = response.body();
                        showToast("Recipe added successfully.");
                    }else{
                        showToast("Communication error occurred. Try again!" + response.message());
                    }
                }

                @Override
                public void onFailure(Call<RezepteTO> call, Throwable t) {
                    showToast("Communication error occurred. Try again!");
                }
            });
        }

    }
    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}