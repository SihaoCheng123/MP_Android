package com.example.mealplanner.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.databinding.ActivityLoginBinding;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiUserService;

import com.example.mealplanner.io.token.TokenManager;
import com.example.mealplanner.io.token.UserIdManager;
import com.example.mealplanner.io.token.UserNameManager;
import com.example.mealplanner.model.dto.ApiDelivery;
import com.example.mealplanner.model.dto.LoginRequest;
import com.example.mealplanner.model.dto.LoginResponse;
import com.example.mealplanner.ui.fragments.FragmentManagerWithNav;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private TokenManager tokenManager;

    private UserIdManager userIdManager;
    private UserNameManager userNameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tokenManager = new TokenManager(this);
        userIdManager = new UserIdManager(this);
        userNameManager = new UserNameManager(this);

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
                            String token = loginResponse.getToken();
                            tokenManager.saveToken(token);
                            if (loginResponse.getId() == null){
                                Log.e("Error", "No id found");
                            }else {
                                Long id = loginResponse.getId();
                                userIdManager.saveUserId(id);
                            }
                            if (loginResponse.getName() == null){
                                Log.e("Error", "No username found");
                            }else {
                                String username = loginResponse.getName();
                                userNameManager.saveUserName(username);
                            }

                            goMain();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ApiDelivery<LoginResponse>> call, @NonNull Throwable throwable) {
                    Toast.makeText(Login.this, "Connection error", Toast.LENGTH_SHORT).show();
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

}