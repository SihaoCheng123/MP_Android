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
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.model.data.Ingredients;

import com.example.mealplanner.model.recycler.ingredientsSimple.IngredientSimpleAdapter;

import java.util.ArrayList;


public class FragmentDetailedRecipeIngredients extends Fragment {
    private ArrayList<Ingredients> ingredientList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_detailed_recipe_ingredients, container, false);
        Bundle bundle = getArguments();

        if (bundle != null){
            ingredientList = (ArrayList<Ingredients>) bundle.getSerializable("ingredientsList");
        }
        if (ingredientList == null || ingredientList.isEmpty()){
            Toast.makeText(getContext(), "No ingredients found", Toast.LENGTH_SHORT).show();
        }else{
            IngredientSimpleAdapter adapter = new IngredientSimpleAdapter(view.getContext(), ingredientList);
            RecyclerView recyclerView = view.findViewById(R.id.ingredientsRecyclerDR);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

    }


}