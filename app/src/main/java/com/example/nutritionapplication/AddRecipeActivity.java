package com.example.nutritionapplication;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.nutritionapplication.databinding.ActivityAddRecipeBinding;
import com.example.nutritionapplication.dto.RezepteTO;
import java.io.ByteArrayOutputStream;
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
    RezepteTO recipe;
    ImageView imgGallery;
    RequestBody rq;
    ActivityResultLauncher<String> mTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityAddRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.myApp = (NutritionAndroidApplication) getApplication();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mTakePhoto = registerForActivityResult(new ActivityResultContracts.GetContent(), result -> binding.recipeImage.setImageURI(result));

        this.imgGallery = findViewById(R.id.recipeImage);
        imgGallery.setOnClickListener(v -> mTakePhoto.launch("image/*"));

        binding.submitRecipie.setOnClickListener((View view) ->{
            showToast("Calling save rezept");
            this.saveRezept();
        });

    }

    public void saveRezept(){
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
        String name = binding.recipenameSubmit.getText().toString();
        String menu = binding.menueTypeSubmit.getText().toString();

        imgGallery.buildDrawingCache();
        Bitmap bitmap = imgGallery.getDrawingCache();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.getEncoder().encodeToString(byteArray);
        rq = RequestBody.create(MediaType.parse("application/json"),encoded);

        if(name.equals("")){
            showToast("No empty recipe name allowed");
        }else{
            Call<RezepteTO> call = this.myApp.getRezepteService().saveRezept
                    ("Bearer "+ this.myApp.getJwt(), name,workingtime,cookingtime,portions,menu,isvegan,isvegetarian,histamine,lactose,fructose,rq);
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<RezepteTO> call, Response<RezepteTO> response) {
                    if (response.isSuccessful()) {
                        recipe = response.body();
                        Intent intent = new Intent(getApplicationContext(), DisplayRezeptActivity.class);
                        Bundle b1 = new Bundle();
                        b1.putInt("wt", recipe.getArbeitszeit());
                        b1.putInt("ct", recipe.getKochzeit());
                        b1.putInt("ot", recipe.getGesamtzeit());
                        b1.putInt("portionen", recipe.getPortionen());
                        b1.putString("menu", recipe.getMenueart());
                        b1.putString("rezeptName", recipe.getName());
                        b1.putString("image", recipe.getImage());
                        b1.putBoolean("vegan", recipe.isVegan());
                        b1.putBoolean("vegetarisch", recipe.isVegetarisch());
                        b1.putBoolean("histamine", recipe.isHistamine());
                        b1.putBoolean("fructose", recipe.isFructose());
                        b1.putBoolean("lactose", recipe.isLactose());
                        b1.putInt("calories", recipe.getKalorien());
                        b1.putInt("proteine", recipe.getProteine());
                        b1.putString("author", recipe.getAuthor());
                        intent.putExtras(b1);
                        startActivity(intent);
                    } else {
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