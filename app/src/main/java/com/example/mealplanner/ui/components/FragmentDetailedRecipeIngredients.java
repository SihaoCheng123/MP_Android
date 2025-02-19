package com.example.mealplanner.ui.components;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.example.mealplanner.model.recycler.ingredientsSimple.IngredientSimpleAdapter;
import com.example.mealplanner.model.recycler.ingredientsSimple.IngredientSimpleEventModel;


import java.util.ArrayList;

public class FragmentDetailedRecipeIngredients extends Fragment {
    private IngredientSimpleAdapter adapter;
    private ArrayList<IngredientSimpleEventModel> ingredientSimpleList = new ArrayList<>();

    public FragmentDetailedRecipeIngredients(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_detailed_recipe_ingredients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        setIngredientEventModels();
        adapter = new IngredientSimpleAdapter(view.getContext(), ingredientSimpleList);
        RecyclerView recyclerView = view.findViewById(R.id.ingredientsRecyclerDR);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setIngredientEventModels(){
        String[] ingredientNames = getResources().getStringArray(R.array.ingredient_name_cv);

        for (int i = 0; i < ingredientNames.length ;i++) {
            ingredientSimpleList.add(new IngredientSimpleEventModel(ingredientNames[i]));
        }
    }

}