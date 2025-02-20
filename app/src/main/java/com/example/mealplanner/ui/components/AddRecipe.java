package com.example.mealplanner.ui.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityAddRecipeBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


import java.util.ArrayList;

public class AddRecipe extends AppCompatActivity {
    private String recipeName, date, category, time;
    private ArrayList<String> stepsList = new ArrayList<>();
    private ArrayList<String> ingredientsList = new ArrayList<>();
    private ArrayList<TextView> categoryList = new ArrayList<>();
    private TextView selectedCategoryTV;
    ActivityAddRecipeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.dateTextAR.setOnClickListener(v -> showCalendar());

        binding.textAddNewStepAR.setOnClickListener(v -> addNewStep());
        addCategory();

        binding.textAddNewIngredientAR.setOnClickListener(v -> addNewIng());

    }

    private void showCalendar(){
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        LayoutInflater inflater = getLayoutInflater();
        View calendarView = inflater.inflate(R.layout.activity_calendar, null);
        CalendarView calendar = calendarView.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> date = dayOfMonth + "/" + (month + 1) + "/" + year);
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

    }

    private void addNewIng(){

        String newIngredient = binding.inputAddIngAR.getEditText().getText().toString().trim();
        if (!newIngredient.isEmpty()){
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
    private void createRecipe(){

    }
}