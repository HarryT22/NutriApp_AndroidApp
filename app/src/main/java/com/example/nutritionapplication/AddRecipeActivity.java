package com.example.nutritionapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nutritionapplication.databinding.ActivityAddRecipeBinding;

import com.example.nutritionapplication.dto.RezepteTO;


import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.Base64;


import okhttp3.MediaType;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRecipeActivity extends AppCompatActivity {

    private NutritionAndroidApplication myApp;
    ActivityAddRecipeBinding binding;
    boolean fructose = false;
    boolean lactose = false;
    boolean histamine = false;
    boolean isvegan = false;
    boolean isvegetarian = false;
    RezepteTO rezepteTO;
    private final int GALLERY_REQ_CODE = 1;
    ImageView imgGallery;
    Uri imageURI;
    Bitmap bitmap;
    RequestBody rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityAddRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();

        this.imgGallery = findViewById(R.id.recipeImage);
        imgGallery.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i,"Select picture"), GALLERY_REQ_CODE);
        });

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

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            String encoded = Base64.getEncoder().encodeToString(byteArray);
            rq = RequestBody.create(MediaType.parse("application/json"),encoded);
            this.saveRezept(binding.recipenameSubmit.getText().toString(),workingtime,cookingtime,
                    portions,binding.menueTypeSubmit.getText().toString(),isvegan,isvegetarian,fructose,lactose,histamine,rq);

        });
    }

    public void saveRezept(String name, int arbeitszeit, int kochzeit, int portionen, String menueart, boolean isVegan, boolean isVegetarisch,
                           boolean fructose, boolean lactose, boolean histamine, RequestBody rq){
        if(name.equals("")){
            showToast("No empty recipe name allowed");
        }else{
            Call<RezepteTO> call = this.myApp.getRezepteService().saveRezept
                    ("Bearer "+ this.myApp.getJwt(), name,arbeitszeit,kochzeit,portionen,menueart,isVegan,isVegetarisch,histamine,lactose,fructose,rq);
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK){
            if (requestCode == RESULT_OK){
                imageURI = data.getData();
                try {
                    this.bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageURI);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}