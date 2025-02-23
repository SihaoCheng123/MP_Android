package com.example.mealplanner.ui.components;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityAddRecipeBinding;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiRecipeService;
import com.example.mealplanner.model.data.Ingredients;
import com.example.mealplanner.model.data.Recipes;
import com.example.mealplanner.model.data.Steps;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRecipe extends AppCompatActivity {
    private String date;
    private String category;
    private final ArrayList<String> stepsList = new ArrayList<>();
    private final ArrayList<String> ingredientsList = new ArrayList<>();
    private final ArrayList<TextView> categoryList = new ArrayList<>();
    private TextView selectedCategoryTV;
    ActivityAddRecipeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iconBackAR.setOnClickListener(v -> finish());
        binding.dateTextAR.setOnClickListener(v -> showCalendar());

        binding.textAddNewStepAR.setOnClickListener(v -> addNewStep());
        addCategory();

        binding.textAddNewIngredientAR.setOnClickListener(v -> addNewIng());
        binding.addRecipeBtnAR.setOnClickListener(v -> {
          Recipes newRecipe = createRecipe();
          Log.e("Recipe", newRecipe.toString());
          sendRecipe(newRecipe);
        });

    }

    private void showCalendar(){
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        LayoutInflater inflater = getLayoutInflater();
        View calendarView = inflater.inflate(R.layout.activity_calendar, null);
        CalendarView calendar = calendarView.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {

            @SuppressLint("DefaultLocale") String formattedMonth = String.format("%02d", (month +1));
            @SuppressLint("DefaultLocale") String formattedDay = String.format("%02d", dayOfMonth);
            date = year +  "-" + (formattedMonth) + "-" + formattedDay;
        });

        alertDialogBuilder.setTitle("Calendar")
                        .setPositiveButton("OK", (dialog, which) -> binding.dateTextAR.setText(date));
        alertDialogBuilder.setView(calendarView);
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    private void addNewStep(){
        String newStep = binding.inputStepsAR.getEditText().getText().toString().trim();

        if (!newStep.isEmpty()) {
            this.stepsList.add(newStep);
            Toast.makeText(this, "Step added", Toast.LENGTH_SHORT).show();
        }
        binding.editStepAR.setText("");
    }

    private void addCategory(){
        categoryList.add(binding.categoryBreakfastAR);
        categoryList.add(binding.categoryLunchAR);
        categoryList.add(binding.categorySnackAR);
        categoryList.add(binding.categoryDinnerAR);

        for (TextView categoryText:categoryList) {
            categoryText.setOnClickListener(v -> selectCategory(categoryText));
        }
    }

    private void selectCategory(TextView selectedCategory){

        if (selectedCategoryTV != null){
            this.selectedCategoryTV.getBackground().setAlpha(255);
        }
        this.selectedCategoryTV = selectedCategory;
        this.selectedCategoryTV.getBackground().setAlpha(120);
        this.category = this.selectedCategoryTV.getText().toString();
    }

    private void addNewIng(){

        String newIngredient = binding.inputAddIngAR.getEditText().getText().toString().trim();
        if (!newIngredient.isEmpty()){
            if (ingredientsList.contains(newIngredient)) {
                Toast.makeText(this,"Ingredient already exists", Toast.LENGTH_SHORT).show();
            }else{
                this.ingredientsList.add(newIngredient);
                int[] backgrounds = {
                        R.drawable.round_category_primary,
                        R.drawable.round_category_grey,
                        R.drawable.round_category_secondary
                };
                int bgIndex = this.ingredientsList.size() % backgrounds.length;
                TextView newIngTV = new TextView(AddRecipe.this);
                newIngTV.setText(newIngredient);
                newIngTV.setTextSize(14);
                newIngTV.setTextColor(ContextCompat.getColor(this, R.color.md_theme_onBackground));
                newIngTV.setBackgroundResource(backgrounds[bgIndex]);
                newIngTV.setPadding(30,20,30,20);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                params.rightMargin = 10;
                newIngTV.setLayoutParams(params);
                binding.ingredientsListLayoutAR.addView(newIngTV);
            }
            binding.editIngAR.setText("");
        }

    }

    private Recipes createRecipe(){
        //Get recipe name
        String recipeName = binding.inputRecipeNameAR.getEditText().getText().toString().trim();
        if (recipeName.isEmpty()){
            Toast.makeText(AddRecipe.this, "Required data", Toast.LENGTH_SHORT).show();
        }
        //Get time
        String time = binding.inputTimeAR.getEditText().getText().toString().trim();
        String rationsString = binding.inputRationsAR.getEditText().getText().toString().trim();
        int rations = Integer.parseInt(rationsString);

        //Get steps list
        Set<Steps> stepsSet = new HashSet<>();
        if (!stepsList.isEmpty()){
            for (int i = 0; i < stepsList.size(); i++) {
                Steps steps = new Steps((i+1), stepsList.get(i));
                stepsSet.add(steps);
            }
        }
        //Get ingredients list
        Set<Ingredients> ingredientsSet = new HashSet<>();
        if(!ingredientsList.isEmpty()){
            for (int i = 0; i < ingredientsList.size(); i++) {
                Ingredients newIng = new Ingredients(ingredientsList.get(i));
                ingredientsSet.add(newIng);
            }
        }
        return new Recipes(recipeName, time, rations, date, "", category, stepsSet, ingredientsSet);
    }

    private void sendRecipe(Recipes recipes){
        ApiRecipeService apiRecipeService = ApiClient.getClient().create(ApiRecipeService.class);
        Call<Recipes> call = apiRecipeService.createRecipe(recipes);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Recipes> call, @NonNull Response<Recipes> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AddRecipe.this, "Recipe created successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddRecipe.this, "Error creating recipe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Recipes> call, @NonNull Throwable throwable) {
                Toast.makeText(AddRecipe.this, "Connection error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

