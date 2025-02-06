package com.example.mealplanner.ui.auth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityLoginBinding;
import com.example.mealplanner.databinding.ActivityRecipeDetailedBinding;
import com.example.mealplanner.ui.fragments.FragmentManagerWithNav;

import java.io.File;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMain();
            }
        });

        binding.createText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });
    }

    private void goMain(){
        Intent intent = new Intent(this, FragmentManagerWithNav.class);
        startActivity(intent);
    }

    private void goRegister(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}