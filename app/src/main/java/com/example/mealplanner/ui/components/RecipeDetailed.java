package com.example.mealplanner.ui.components;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityRecipeDetailedBinding;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiRecipeService;
import com.example.mealplanner.model.data.Ingredients;
import com.example.mealplanner.model.data.Recipes;
import com.example.mealplanner.model.recycler.ViewPagerAdapterDR;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipeDetailed extends AppCompatActivity {

    ActivityRecipeDetailedBinding binding;
    private Set<Ingredients> ingredientsSet = new HashSet<>();

    private ArrayList<Ingredients> ingredientsList;
    private Recipes newRecipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_detailed);
        binding = ActivityRecipeDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Long recipeId = getIntent().getLongExtra("recipeId", -1);

        binding.iconBackDR.setOnClickListener(v -> finish());
        getRecipeById(recipeId);



    }

    private void getRecipeById(Long recipeId){
        ApiRecipeService apiRecipeService = ApiClient.getClient().create(ApiRecipeService.class);
        Call<Recipes> call = apiRecipeService.getRecipeById(recipeId);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Recipes> call, @NonNull Response<Recipes> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        newRecipe = response.body();
                        binding.recipeNameDR.setText(newRecipe.getName());
                        binding.recipeTimeTextDR.setText(newRecipe.getTime());
                        binding.servingsAmountDR.setText(String.valueOf(newRecipe.getRations()));
                        ingredientsSet = newRecipe.getIngredients();

                        if(!ingredientsSet.isEmpty()){
                            ingredientsList = new ArrayList<>(ingredientsSet);
                            if (!ingredientsList.isEmpty()){
                                ViewPager2 viewPager2 = findViewById(R.id.viewPagerDR);
                                ViewPagerAdapterDR viewPagerAdapterDR = new ViewPagerAdapterDR(RecipeDetailed.this, recipeId, ingredientsList);
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


                    } else {
                        Toast.makeText(RecipeDetailed.this, "Error in fetching recipe", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Recipes> call, @NonNull Throwable throwable) {
                Toast.makeText(RecipeDetailed.this, "Connection error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}