package com.example.mealplanner.io.api;

import com.example.mealplanner.model.data.Users;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiUserService {

    @POST("/users/create")
    Call<Users> createUser();

//    @POST("/users/login")
//    Call<>
}
