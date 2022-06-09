/*package com.example.nutritionapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.bnLogin.setEnabled(false);
                doLogin();
            }
        });

        binding.linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {}

    public void doLogin () {
        String username = binding.inputLoginUsername.getText().toString();
        String password = binding.inputLoginPassword.getText().toString();
        UserTO userTO = new UserTO(username, password);

        final ShoppingListAndroidApplication myApp = (ShoppingListAndroidApplication) getApplication();

        Call<ResponseBody> call = myApp.getUserService().login(userTO);
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
                    Intent intent = new Intent(getApplicationContext(), ShoppingListActivity.class);
                    startActivity(intent);

                } else {
                    showToast("Login failed: Check Credentials");
                    binding.bnLogin.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showToast("Communication error occured");
                binding.bnLogin.setEnabled(true);
                Log.e("SignInActivity", "Communication error: " + t.getMessage());
            }
        });
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
*/