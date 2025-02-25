package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiRecipeService;
import com.example.mealplanner.io.token.UserIdManager;
import com.example.mealplanner.model.data.Ingredients;
import com.example.mealplanner.model.recycler.ingredientsComplex.IngredientAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCart extends Fragment {

   List<Ingredients> ingredientsList = new ArrayList<>();
   private RecyclerView recyclerView;
   private IngredientAdapter adapter;
   private UserIdManager userIdManager;

    public ShoppingCart() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        LocalDate today = LocalDate.now();
        recyclerView = view.findViewById(R.id.ingredientsRecycler);
        userIdManager = new UserIdManager(container.getContext());
        loadIngredients(formatDate(today));
        return view;
    }

    private String formatDate(LocalDate selectedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(selectedDate);
    }

    private void loadIngredients(String date){
        ApiRecipeService apiRecipeService = ApiClient.getClient().create(ApiRecipeService.class);
        Call<List<Ingredients>> call = apiRecipeService.getIngredientsFromThisWeekAndUser(date, userIdManager.getUserId());
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<Ingredients>> call, @NonNull Response<List<Ingredients>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ingredientsList = response.body();
                    if (!ingredientsList.isEmpty()) {
                        Toast.makeText(getContext(), "Success in fetching ingredients", Toast.LENGTH_SHORT).show();
                        adapter = new IngredientAdapter(getContext(), ingredientsList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                } else {
                    Toast.makeText(getContext(), "Error fetching recipes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Ingredients>> call, @NonNull Throwable throwable) {
                Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}