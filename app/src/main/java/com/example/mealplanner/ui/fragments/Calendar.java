package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiRecipeService;
import com.example.mealplanner.io.viewModel.ViewModel;
import com.example.mealplanner.model.data.Recipes;
import com.example.mealplanner.ui.components.CalendarWeek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Calendar extends Fragment {

    private List<Recipes> recipesList = new ArrayList<>();

    public Calendar() {
        super(R.layout.fragment_calendar);
    }

    public static Calendar newInstance() {
        return new Calendar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewModel dateViewModel = new ViewModelProvider(this).get(ViewModel.class);
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        if (savedInstanceState == null){
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.calendarContainerCS, new CalendarWeek());
            transaction.commit();
        }
        LocalDate selectedDate = LocalDate.now();
        dateViewModel.getSelectedDate().observe(getViewLifecycleOwner(), newDate-> getRecipeByDate(formatDate(newDate)));

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private String formatDate(LocalDate selectedDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(selectedDate);
    }

    private void getRecipeByDate(String date){
        ApiRecipeService apiRecipeService = ApiClient.getClient().create(ApiRecipeService.class);
        Call<List<Recipes>> call = apiRecipeService.getRecipesByDate(date);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipes>> call, @NonNull Response<List<Recipes>> response) {
                if (response.isSuccessful()) {
                    recipesList = response.body();
                    if (!recipesList.isEmpty()) {
                        showRecipes(recipesList);
                    } else {
                        showRecipes(new ArrayList<>());
                    }

                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipes>> call, @NonNull Throwable throwable) {
                Toast.makeText(getContext(), "Error trayendo receta", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showRecipes(List<Recipes> recipes){
        boolean breakFastAdded = false;
        boolean lunchAdded = false;
        boolean snackAdded = false;
        boolean dinnerAdded = false;

        FrameLayout breakfastLayout = getView().findViewById(R.id.breakfastLayoutCS);
        FrameLayout lunchLayout = getView().findViewById(R.id.lunchLayoutCS);
        FrameLayout snackLayout = getView().findViewById(R.id.snackLayoutCS);
        FrameLayout dinnerLayout = getView().findViewById(R.id.dinnerLayoutCS);

        // Limpiar los layouts antes de agregar nuevas recetas
        breakfastLayout.removeAllViews();
        lunchLayout.removeAllViews();
        snackLayout.removeAllViews();
        dinnerLayout.removeAllViews();

        if (!recipes.isEmpty()){

            for (Recipes recipe: recipes){
                if (recipe.getCategory().equals("Breakfast")){
                    inflateRecipeCard(recipe, R.id.breakfastLayoutCS, R.drawable.round_card_white);
                    breakFastAdded = true;
                } else if (recipe.getCategory().equals("Lunch")) {
                    inflateRecipeCard(recipe, R.id.lunchLayoutCS, R.drawable.round_card_secondary);
                    lunchAdded = true;
                }else if (recipe.getCategory().equals("Snack")) {
                    inflateRecipeCard(recipe, R.id.snackLayoutCS, R.drawable.round_card_primary);
                    snackAdded = true;
                }else if (recipe.getCategory().equals("Dinner")) {
                    inflateRecipeCard(recipe, R.id.dinnerLayoutCS, R.drawable.round_card_white);
                    dinnerAdded = true;
                }

            }
        }
        if (breakFastAdded){
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) breakfastLayout.getLayoutParams();
            params.topMargin = 270;
            breakfastLayout.setLayoutParams(params);
        }else {
            inflateNoRecipeCard(R.id.breakfastLayoutCS, R.drawable.round_card_white);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) breakfastLayout.getLayoutParams();
            params.topMargin = 0;
            breakfastLayout.setLayoutParams(params);
        }
        if (!lunchAdded) {
            inflateNoRecipeCard(R.id.lunchLayoutCS, R.drawable.round_card_secondary);
        }
        if (!snackAdded){
            inflateNoRecipeCard(R.id.snackLayoutCS, R.drawable.round_card_primary);
        }
        if (!dinnerAdded){
            inflateNoRecipeCard(R.id.dinnerLayoutCS, R.drawable.round_card_white);
        }
    }

    private void inflateRecipeCard(Recipes recipes,int layoutId, int bgResource){
        FrameLayout recipeLayout = getView().findViewById(layoutId);
        View recipeView = getLayoutInflater().inflate(R.layout.cardview_recipe, recipeLayout,false);
        TextView recipeName = recipeView.findViewById(R.id.recipeName);
        recipeName.setText(recipes.getName());
        recipeView.setBackgroundResource(bgResource);

        recipeLayout.addView(recipeView);
    }
    private void inflateNoRecipeCard(int layoutId, int bgResourse){
        FrameLayout recipeLayout = getView().findViewById(layoutId);
        View noRecipeView = getLayoutInflater().inflate(R.layout.cardview_null_recipe, recipeLayout,false);
        noRecipeView.setBackgroundResource(bgResourse);
        recipeLayout.addView(noRecipeView);
    }
}