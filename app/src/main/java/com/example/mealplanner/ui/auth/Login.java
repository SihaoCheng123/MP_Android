package com.example.mealplanner.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.databinding.ActivityLoginBinding;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiUserService;

import com.example.mealplanner.model.dto.ApiDelivery;
import com.example.mealplanner.model.dto.LoginRequest;
import com.example.mealplanner.model.dto.LoginResponse;
import com.example.mealplanner.ui.fragments.FragmentManagerWithNav;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.contButton.setOnClickListener(v -> login());

        binding.createText.setOnClickListener(v -> goRegister());
    }

    private void login(){
        String email = binding.emailtext.getEditText().getText().toString().trim();
        String password = binding.passwordToggle.getEditText().getText().toString().trim();
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Required data", Toast.LENGTH_SHORT).show();
        }else{
            ApiUserService apiUserService = ApiClient.getClient().create(ApiUserService.class);
            LoginRequest loginRequest = new LoginRequest(email, password);
            Call<ApiDelivery<LoginResponse>> call = apiUserService.login(loginRequest);
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(@NonNull Call<ApiDelivery<LoginResponse>> call, @NonNull Response<ApiDelivery<LoginResponse>> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            LoginResponse loginResponse = response.body().getData();
                            Log.e("Respuestas: ", loginResponse.getEmail() + loginResponse.getPassword());
                            if (!loginRequest.getEmail().equals(loginResponse.getEmail())) {
                                Toast.makeText(Login.this, "User not found", Toast.LENGTH_SHORT).show();
                            }
                            if (!loginRequest.getPassword().equals(loginResponse.getPassword())) {
                                Toast.makeText(Login.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                            String token = loginResponse.getToken();
                            saveToken(token);
                            goMain();

                        }
                    }
                }


                @Override
                public void onFailure(@NonNull Call<ApiDelivery<LoginResponse>> call, @NonNull Throwable throwable) {

                }
            });
        }


    }
    private void goMain(){
        Intent intent = new Intent(this, FragmentManagerWithNav.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void goRegister(){
        Intent intent = new Intent(this, Register.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void saveToken(String token){
        SharedPreferences sharedPreferences = getSharedPreferences("WeatlyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userToken", token);
        editor.apply();
    }
}