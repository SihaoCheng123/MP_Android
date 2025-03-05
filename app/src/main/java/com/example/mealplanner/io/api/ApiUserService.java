package com.example.mealplanner.io.api;

import com.example.mealplanner.model.data.Users;
import com.example.mealplanner.model.dto.ApiDelivery;
import com.example.mealplanner.model.dto.LoginRequest;
import com.example.mealplanner.model.dto.LoginResponse;
import com.example.mealplanner.model.dto.PasswordChangeRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiUserService {

    @POST("users/create")
    Call<Users> createUser(@Body Users user);


    @POST("users/login")
    Call<ApiDelivery<LoginResponse>> login(@Body LoginRequest loginRequest);

    @PATCH("users/update/{id}")
    Call<Users> updateUser(@Body Users user, @Path("id") Long user_id);

    @PUT("users/change-password/{id}")
    Call<Users> changePassword(@Body PasswordChangeRequest passwordChangeRequest, @Path("id") Long user_id);
}
