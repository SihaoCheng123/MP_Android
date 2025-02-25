package com.example.mealplanner.model.recycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mealplanner.model.recycler.ingredientsSimple.FragmentDetailedRecipeIngredients;
import com.example.mealplanner.model.recycler.stepsSimple.FragmentDetailedRecipeSteps;
import com.example.mealplanner.model.data.Ingredients;
import com.example.mealplanner.model.data.Steps;


import java.util.ArrayList;


public class ViewPagerAdapterDR extends FragmentStateAdapter {

    private ArrayList<Ingredients> ingredientsList;

    private ArrayList<Steps> stepsList;
    public ViewPagerAdapterDR(AppCompatActivity activity, ArrayList<Ingredients> ingredientsList, ArrayList<Steps> stepsList){
        super(activity);
        this.ingredientsList = ingredientsList;
        this.stepsList = stepsList;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("ingredientsList", ingredientsList);
        bundle.putSerializable("stepsList", stepsList);
        switch (position) {
            case 0:
                FragmentDetailedRecipeIngredients ingFragment = new FragmentDetailedRecipeIngredients();
                ingFragment.setArguments(bundle);
                return ingFragment;
            case 1:
                FragmentDetailedRecipeSteps stepFragment = new FragmentDetailedRecipeSteps();
                stepFragment.setArguments(bundle);
                return stepFragment;
            default:
                return new Fragment();
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
