package com.example.nutritionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nutritionapplication.databinding.ActivitySingInBinding;
import com.example.nutritionapplication.dto.LoginTo;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingInActivity extends AppCompatActivity {
private ActivitySingInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding= ActivitySingInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    binding.button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            binding.button.setEnabled(false);
            doLogin();
        }
    });
    binding.button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(),SingUpActivity.class);
        startActivity(intent);
        }
    });
    }
   @Override
   public void onBackPressed(){

   }
   public void doLogin(){
        String email=binding.editTextTextEmailAddress.getText().toString();
        String password=binding.editTextTextPassword.getText().toString();
       LoginTo loginTo= new LoginTo(email,password);
       final NutritionAndroidApplication myApp= (NutritionAndroidApplication) getApplication();
       Call<ResponseBody> call = myApp.getIUserService().login(loginTo);
       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               if(response.isSuccessful()) {
                   try {
                       String jwt = response.body().string();
                       myApp.setJwt(jwt);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   Intent intent = new Intent(getApplicationContext(),MealActivity.class);
                   startActivity(intent);

               } else {
                   showToast("Login failed: Check Credentials");
                   binding.button.setEnabled(true);
               }
           }


           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {
               showToast("Communication error occured");
               binding.button.setEnabled(true);
               Log.e("SignInActivity", "Communication error: " + t.getMessage());
           }
       });
   }
    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

}