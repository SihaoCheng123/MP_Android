package com.example.mealplanner.ui.auth;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.databinding.ActivityRegisterBinding;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiUserService;
import com.example.mealplanner.model.data.User_Data;
import com.example.mealplanner.model.data.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRegister.setOnClickListener(v -> register());
        binding.existingAccount.setOnClickListener(v -> goLogin());
    }

    private void register(){

            String email = binding.email.getEditText().getText().toString().trim();
            String password = binding.password.getEditText().getText().toString().trim();
            String confirmPassword = binding.confirmPassword.getEditText().getText().toString().trim();

            String name = binding.name.getEditText().getText().toString().trim();
            String age = binding.age.getEditText().getText().toString().trim();
            String phone = binding.phone.getEditText().getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
        || name.isEmpty() || age.isEmpty() || phone.isEmpty()){
            Toast.makeText(this, "Required data", Toast.LENGTH_SHORT).show();
        }else if (!password.equals(confirmPassword)){
            Toast.makeText(this, "Passwords must match", Toast.LENGTH_SHORT).show();
        }else{
            int ageInt = Integer.parseInt(age);
            ApiUserService apiUserService = ApiClient.getClient().create(ApiUserService.class);
            User_Data newUser_Data = new User_Data(name, ageInt, phone);
            Users newUser = new Users(email, password,newUser_Data);

            Call<Users> call = apiUserService.createUser(newUser);
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(Register.this, "Register success", Toast.LENGTH_SHORT).show();
                        goLogin();
                    } else {
                        Toast.makeText(Register.this, "Error in creating account", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Users> call, @NonNull Throwable throwable) {
                    Toast.makeText(Register.this, "Connection error", Toast.LENGTH_SHORT).show();
                }

            });
        }

    }

    private void goLogin(){
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}