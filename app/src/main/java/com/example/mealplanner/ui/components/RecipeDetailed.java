package com.example.mealplanner.ui.components;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityRecipeDetailedBinding;
import com.example.mealplanner.model.recycler.ingredientsComplex.IngredientEventModel;
import com.example.mealplanner.model.recycler.ingredientsSimple.IngredientSimpleAdapter;
import com.example.mealplanner.model.recycler.ingredientsSimple.IngredientSimpleEventModel;
import com.example.mealplanner.model.recycler.stepsSimple.StepsSimpleAdapter;
import com.example.mealplanner.model.recycler.stepsSimple.StepsSimpleEventModel;
import com.example.mealplanner.ui.auth.Login;
import com.example.mealplanner.ui.fragments.HomeScreen;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class RecipeDetailed extends AppCompatActivity {

    ArrayList<IngredientSimpleEventModel> ingredientsList = new ArrayList<>();
    ArrayList<StepsSimpleEventModel> stepsList = new ArrayList<>();
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
//        setIngredientEventModels();
//        IngredientSimpleAdapter ingredientSimpleAdapteradapter = new IngredientSimpleAdapter(this, ingredientsList);
//        binding.ingredientsRecyclerDR.setAdapter(ingredientSimpleAdapteradapter);
//        binding.ingredientsRecyclerDR.setLayoutManager(new LinearLayoutManager(this));

        setStepsEventModels();
        StepsSimpleAdapter stepsSimpleAdapter = new StepsSimpleAdapter(this, stepsList);
        binding.ingredientsRecyclerDR.setAdapter(stepsSimpleAdapter);
        binding.ingredientsRecyclerDR.setLayoutManager(new LinearLayoutManager(this));
        binding.tabLayoutDR.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void setIngredientEventModels(){
        String[] ingredientNames = getResources().getStringArray(R.array.ingredient_name_cv);

        for (int i = 0; i < ingredientNames.length; i++) {
            ingredientsList.add(new IngredientSimpleEventModel(ingredientNames[i]));
        }
    }

    private void setStepsEventModels(){
        String [] stepsDescriptions = getResources().getStringArray(R.array.step_description_cv);
        for (int i = 0; i < stepsDescriptions.length; i++){
            stepsList.add(new StepsSimpleEventModel(String.valueOf(i+1), stepsDescriptions[i]));
        }
    }
}