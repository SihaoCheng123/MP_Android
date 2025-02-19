package com.example.mealplanner.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityLoginBinding;
import com.example.mealplanner.ui.fragments.FragmentManagerWithNav;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.contButton.setOnClickListener(v -> goMain());

        binding.createText.setOnClickListener(v -> goRegister());
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