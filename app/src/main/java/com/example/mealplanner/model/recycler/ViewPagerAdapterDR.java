package com.example.mealplanner.model.recycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mealplanner.model.data.Ingredients;
import com.example.mealplanner.ui.components.FragmentDetailedRecipeIngredients;
import com.example.mealplanner.ui.components.FragmentDetailedRecipeSteps;

import java.util.ArrayList;

public class ViewPagerAdapterDR extends FragmentStateAdapter {

    private Long recipeId;
    private ArrayList<Ingredients> ingredientsList;
    public ViewPagerAdapterDR(AppCompatActivity activity, Long recipeId, ArrayList<Ingredients> ingredientsList){
        super(activity);
        this.recipeId = recipeId;
        this.ingredientsList = ingredientsList;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("ingredientsList", ingredientsList);
        switch (position) {
            case 0:
                FragmentDetailedRecipeIngredients ingFragment = new FragmentDetailedRecipeIngredients();
                ingFragment.setArguments(bundle);
                return ingFragment;
            case 1:
                return new FragmentDetailedRecipeSteps();
            default:
                return new Fragment();
        }
    }

    public void updateIngredients(ArrayList<Ingredients> newIngs){
        this.ingredientsList = newIngs;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
