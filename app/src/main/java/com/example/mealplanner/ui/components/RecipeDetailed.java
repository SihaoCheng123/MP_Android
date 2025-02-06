package com.example.mealplanner.ui.components;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityRecipeDetailedBinding;
import com.example.mealplanner.model.recycler.ViewPagerAdapterDR;
import com.example.mealplanner.ui.fragments.HomeScreen;
import com.google.android.material.tabs.TabLayoutMediator;


public class RecipeDetailed extends AppCompatActivity {

    ActivityRecipeDetailedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_detailed);
        binding = ActivityRecipeDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iconBackDR.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
            startActivity(intent);
        });

        ViewPager2 viewPager2 = findViewById(R.id.viewPagerDR);
        ViewPagerAdapterDR viewPagerAdapterDR = new ViewPagerAdapterDR(this);
        binding.viewPagerDR.setAdapter(viewPagerAdapterDR);
        new TabLayoutMediator(binding.tabLayoutDR, viewPager2, (tab, position) -> {
            if (position == 0) {
                tab.setText(R.string.ingredients);
            } else {
                tab.setText(R.string.steps);
            }
        }).attach();
    }


}