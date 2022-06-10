package com.example.nutritionapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.nutritionapplication.databinding.ActivitySignUpBinding;
import com.example.nutritionapplication.dto.AppUserZiele;
import com.example.nutritionapplication.dto.Gender;
import com.example.nutritionapplication.dto.RegistrationTo;
import com.example.nutritionapplication.dto.Role;


import android.widget.Toast;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.button4.setEnabled(false);
                doRegister();
            }
        }
        );

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }
    public void doRegister(){

        RegistrationTo registerDto = null;
        String email= ((TextView) findViewById(R.id.editTextTextEmailAddress2)).getText().toString();
        String name= ((TextView) findViewById(R.id.editTextTextPersonName3)).getText().toString();
        String password= ((TextView) findViewById(R.id.editTextTextPassword2)).getText().toString();
        String UserName= ((TextView) findViewById(R.id.editTextTextPersonName4)).getText().toString();
        String gewicht=((TextView) findViewById(R.id.editTextNumber)).getText().toString();
        Integer gewichtZ =  Integer.parseInt(gewicht);
        short endgewicht = gewichtZ.shortValue();
        String groesse= ((TextView) findViewById(R.id.editTextNumber2)).getText().toString();
        Integer groessez =  Integer.parseInt(gewicht);
        short endgroesse = groessez.shortValue();
        String geburtsdatum = ((TextView) findViewById(R.id.editTextDate2)).getText().toString();
        Boolean isFemale= ((RadioButton) findViewById(R.id.radioButton)).isChecked();
        Boolean isMale= ((RadioButton) findViewById(R.id.radioButton2)).isChecked();
        Boolean isDiverse= ((RadioButton) findViewById(R.id.radioButton3)).isChecked();
        Boolean isMuskelAufbauen= ((RadioButton) findViewById(R.id.radioButton4)).isChecked();
        Boolean isGewichtVerlieren= ((RadioButton) findViewById(R.id.radioButton5)).isChecked();

        if(isFemale && isMuskelAufbauen){
            registerDto = new RegistrationTo(name,UserName,email,password, AppUserZiele.MUSKELN_AUFBAUEN,endgroesse,endgewicht,geburtsdatum,Gender.FEMALE,Role.NORMAL);
        }
        if(isMale && isMuskelAufbauen){
            registerDto = new RegistrationTo(name,UserName,email,password,AppUserZiele.MUSKELN_AUFBAUEN,endgroesse,endgewicht,geburtsdatum, Gender.MALE,Role.NORMAL);
        }
        if(isFemale && isGewichtVerlieren){
            registerDto = new RegistrationTo(name,UserName,email,password,AppUserZiele.GEWICHT_VERLIEREN,endgroesse,endgewicht,geburtsdatum,Gender.FEMALE,Role.NORMAL);
        }
        if(isMale && isGewichtVerlieren){
            registerDto = new RegistrationTo(name,UserName,email,password,AppUserZiele.GEWICHT_VERLIEREN,endgroesse,endgewicht,geburtsdatum,Gender.MALE,Role.NORMAL);
        }
        if(isDiverse&& isMuskelAufbauen){
            registerDto = new RegistrationTo(name,UserName,email,password,AppUserZiele.MUSKELN_AUFBAUEN,endgroesse,endgewicht,geburtsdatum, Gender.DIVERSE,Role.NORMAL);
        }
        if(isDiverse && isGewichtVerlieren){
            registerDto = new RegistrationTo(name,UserName,email,password,AppUserZiele.GEWICHT_VERLIEREN,endgroesse,endgewicht,geburtsdatum, Gender.DIVERSE, Role.NORMAL);
        }

        final NutritionAndroidApplication myApp = (NutritionAndroidApplication) getApplication();

        Call<ResponseBody> call = myApp.getIUserService().register(registerDto);
        call.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
                        String jwt = response.body().string();
                        myApp.setJwt(jwt);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);

                } else {
                    showToast("Register failed: Check Credentials");
                    binding.button4.setEnabled(true);
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showToast("Communication error occured");
                binding.button4.setEnabled(true);
            }
        });
    }
    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}

