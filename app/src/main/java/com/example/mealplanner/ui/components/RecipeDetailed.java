package com.example.mealplanner.ui.components;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityRecipeDetailedBinding;
import com.example.mealplanner.ui.auth.Login;
import com.example.mealplanner.ui.fragments.HomeScreen;

public class RecipeDetailed extends AppCompatActivity {
    ActivityRecipeDetailedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_detailed);
        binding = ActivityRecipeDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.iconBackDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(intent);
            }
        });
    }
}