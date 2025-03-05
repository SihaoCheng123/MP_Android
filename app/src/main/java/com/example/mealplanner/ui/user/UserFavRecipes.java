package com.example.mealplanner.ui.user;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mealplanner.databinding.ActivityUserFavRecipesBinding;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiRecipeService;
import com.example.mealplanner.io.token.UserIdManager;
import com.example.mealplanner.model.data.Recipes;
import com.example.mealplanner.model.recycler.userRecipes.UserRecipesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFavRecipes extends AppCompatActivity {

    private ActivityUserFavRecipesBinding binding;
    private List<Recipes> userFavRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserFavRecipesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getUserFavRecipes();
        binding.iconBackUFR.setOnClickListener(v -> finish());
    }

    private void getUserFavRecipes(){
        UserIdManager userIdManager = new UserIdManager(this);
        ApiRecipeService apiRecipeService = ApiClient.getClient().create(ApiRecipeService.class);
        Call<List<Recipes>> call = apiRecipeService.getUserFavRecipes(userIdManager.getUserId());
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipes>> call, @NonNull Response<List<Recipes>> response) {
                if (response.isSuccessful()) {
                    if (!response.body().isEmpty()) {
                        userFavRecipes = response.body();
                        setRecycler();
                    } else {
                        Toast.makeText(getApplicationContext(), "No recipes prepared", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipes>> call, @NonNull Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Connection error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecycler(){

        UserRecipesAdapter adapter = new UserRecipesAdapter(getApplicationContext(), (ArrayList<Recipes>) userFavRecipes);
        binding.recyclerUFR.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerUFR.setAdapter(adapter);
    }
}