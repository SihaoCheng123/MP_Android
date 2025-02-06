package com.example.mealplanner.model.recycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mealplanner.model.recycler.ingredientsSimple.FragmentDetailedRecipeIngredients;
import com.example.mealplanner.model.recycler.stepsSimple.FragmentDetailedRecipeSteps;

public class ViewPagerAdapterDR extends FragmentStateAdapter {

    public ViewPagerAdapterDR(AppCompatActivity activity){
        super(activity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentDetailedRecipeIngredients();
            case 1:
                return new FragmentDetailedRecipeSteps();
            default:
                return new Fragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
