package com.example.mealplanner.io.api;

import com.example.mealplanner.model.data.Recipes;
import com.example.mealplanner.model.data.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRecipeService {

    @POST("recipes/create")
    Call<Recipes> createRecipe(@Body Recipes recipes);
}
