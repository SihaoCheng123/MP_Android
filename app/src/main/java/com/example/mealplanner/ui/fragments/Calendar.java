package com.example.mealplanner.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.mealplanner.io.token.UserIdManager;
import com.example.mealplanner.io.viewModel.ViewModel;
import com.example.mealplanner.model.data.Ingredients;
import com.example.mealplanner.model.data.Recipes;
import com.example.mealplanner.ui.components.AddRecipe;
import com.example.mealplanner.ui.components.CalendarWeek;
import com.example.mealplanner.ui.components.RecipeDetailed;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Calendar extends Fragment {

    private List<Recipes> recipesList = new ArrayList<>();
    private ViewModel dateViewModel;
    private UserIdManager userIdManager;

    public Calendar() {
        super(R.layout.fragment_calendar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dateViewModel = new ViewModelProvider(this).get(ViewModel.class);
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        if (savedInstanceState == null){
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.calendarContainerCS, new CalendarWeek());
            transaction.commit();
        }
        userIdManager = new UserIdManager(container.getContext());
        dateViewModel.getSelectedDate().observe(getViewLifecycleOwner(), newDate-> {
            getRecipeByDate(formatDate(newDate));
            TextView actualDay = view.findViewById(R.id.actualDayTextCS);
            actualDay.setText(formatDateDayMonth(newDate));
        });
        LocalDate selectedDate = LocalDate.now();
        TextView actualDay = view.findViewById(R.id.actualDayTextCS);
        actualDay.setText(formatDateDayMonth(selectedDate));

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

    private String formatDateDayMonth(LocalDate selectedDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("MMM dd"));
        return formatter.format(selectedDate);
    }

    private void getRecipeByDate(String date){
        ApiRecipeService apiRecipeService = ApiClient.getClient().create(ApiRecipeService.class);
        Call<List<Recipes>> call = apiRecipeService.getRecipesByDateAndUserId(date, userIdManager.getUserId());
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
                Toast.makeText(getContext(), "Error fetching recipes", Toast.LENGTH_SHORT).show();
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
        breakfastLayout.removeAllViews();
        lunchLayout.removeAllViews();
        snackLayout.removeAllViews();
        dinnerLayout.removeAllViews();
        int addedCount = 0;
        if (!recipes.isEmpty()){

            for (Recipes recipe: recipes){
                if (recipe.getCategory().equals("Breakfast")){
                    inflateRecipeCard(recipe, R.id.breakfastLayoutCS, R.drawable.round_card_white);
                    breakFastAdded = true;
                    addedCount++;
                } else if (recipe.getCategory().equals("Lunch")) {
                    inflateRecipeCard(recipe, R.id.lunchLayoutCS, R.drawable.round_card_secondary);
                    lunchAdded = true;
                    addedCount++;
                }else if (recipe.getCategory().equals("Snack")) {
                    inflateRecipeCard(recipe, R.id.snackLayoutCS, R.drawable.round_card_primary);
                    snackAdded = true;
                    addedCount++;
                }else if (recipe.getCategory().equals("Dinner")) {
                    inflateRecipeCard(recipe, R.id.dinnerLayoutCS, R.drawable.round_card_white);
                    dinnerAdded = true;
                    addedCount++;
                }

            }
        }
        if (!breakFastAdded){
            inflateNoRecipeCard(R.id.breakfastLayoutCS, R.drawable.round_card_white);
            goAddRecipe(breakfastLayout);
        }
        if (!lunchAdded) {
            inflateNoRecipeCard(R.id.lunchLayoutCS, R.drawable.round_card_secondary);
            goAddRecipe(lunchLayout);
        }
        if (!snackAdded){
            inflateNoRecipeCard(R.id.snackLayoutCS, R.drawable.round_card_primary);
            goAddRecipe(snackLayout);
        }
        if (!dinnerAdded){
            inflateNoRecipeCard(R.id.dinnerLayoutCS, R.drawable.round_card_white);
            goAddRecipe(dinnerLayout);
        }
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) breakfastLayout.getLayoutParams();
        if (addedCount == 1){
            params.topMargin = 40;
        } else if (addedCount == 2) {
            params.topMargin = 100;
        } else if (addedCount == 3) {
            params.topMargin = 230;
        } else if (addedCount == 4) {
            params.topMargin = 360;
        }

        breakfastLayout.setLayoutParams(params);
    }

    private void inflateRecipeCard(Recipes recipes,int layoutId, int bgResource){
        FrameLayout recipeLayout = getView().findViewById(layoutId);
        View recipeView = getLayoutInflater().inflate(R.layout.cardview_recipe, recipeLayout,false);
        TextView recipeName = recipeView.findViewById(R.id.recipeName);
        recipeName.setText(recipes.getName());
        recipeView.setBackgroundResource(bgResource);
        TextView ing1 = recipeView.findViewById(R.id.recipeIng1);
        TextView ing2 = recipeView.findViewById(R.id.recipeIng2);
        TextView ing3 = recipeView.findViewById(R.id.recipeIng3);
        List<TextView> ingViews = new ArrayList<>();
        ingViews.add(ing1);
        ingViews.add(ing2);
        ingViews.add(ing3);
        int i = 0;
        if (!recipes.getIngredients().isEmpty()){
            for (Ingredients ing : recipes.getIngredients()){
                if (i < ingViews.size()){
                    ingViews.get(i).setText(ing.getName());
                    i++;
                }
            }
        }
        recipeView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), RecipeDetailed.class);
            intent.putExtra("recipeId", recipes.getId());
            startActivity(intent);
        });
        recipeLayout.addView(recipeView);
    }
    private void inflateNoRecipeCard(int layoutId, int bgResource){
        FrameLayout recipeLayout = getView().findViewById(layoutId);
        View noRecipeView = getLayoutInflater().inflate(R.layout.cardview_null_recipe, recipeLayout,false);
        noRecipeView.setBackgroundResource(bgResource);
        FrameLayout breakfastLayout = getView().findViewById(R.id.breakfastLayoutCS);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) breakfastLayout.getLayoutParams();
        params.topMargin = 50;
        breakfastLayout.setLayoutParams(params);
        recipeLayout.addView(noRecipeView);
    }

    private void goAddRecipe(FrameLayout layout){
        layout.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddRecipe.class);
            startActivity(intent);
        });
    }
//

}