package com.example.mealplanner.io.api;
import com.example.mealplanner.model.data.Ingredients;
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
    @POST("recipes/create/{user_id}")
    Call<Recipes> createRecipe(@Body Recipes recipes, @Path("user_id") Long id);

    @Headers("Content-Type: application/json")
    @POST("recipes/fav-recipes/{recipe_id}/{user_id}")
    Call<Recipes> makeRecipeFav(@Path("recipe_id") Long recipe_id, @Path("user_id") Long user_id);

    @GET("recipes/get-by-date/{date}")
    Call<List<Recipes>> getRecipesByDate(@Path("date") String date);

    @GET("recipes/get-by-date-and-user/{date}/{user_id}")
    Call<List<Recipes>> getRecipesByDateAndUserId(@Path("date") String date, @Path("user_id") Long id);

    @GET("recipes/get/{id}")
    Call<Recipes> getRecipeById(@Path("id") Long id);

    @GET("recipes/weekly-ingredients/{date}")
    Call<List<Ingredients>> getIngredientsFromThisWeek(@Path("date") String date);

    @GET("recipes/weekly-ingredients-user/{date}/{id}")
    Call<List<Ingredients>> getIngredientsFromThisWeekAndUser(@Path("date") String date, @Path("id") Long user_id);
}
