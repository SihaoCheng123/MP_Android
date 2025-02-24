package com.example.mealplanner.io.api;
import com.example.mealplanner.model.data.Recipes;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiRecipeService {

    @Headers("Content-Type: application/json")
    @POST("recipes/create")
    Call<Recipes> createRecipe(@Body Recipes recipes);

    @GET("recipes/get-by-date/{date}")
    Call<List<Recipes>> getRecipesByDate(@Path("date") String date);

    @GET("recipes/get/{id}")
    Call<Recipes> getRecipeById(@Path("id") Long id);
}
