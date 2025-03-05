package com.example.mealplanner.io.token;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mealplanner.model.data.Users;
import com.google.gson.Gson;

public class UserManager {
    final String PREFS_NAME = "user_data";
    private static final String USER = "user";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    public UserManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    public Users getUser() {
        String userJson = sharedPreferences.getString(USER, null);
        if (userJson != null){
            return gson.fromJson(userJson, Users.class);
        }
        return null;
    }
    public void saveUser(Users user) {
        String userJson = gson.toJson(user);
        editor.putString(USER, userJson);
        editor.apply();
    }

    public void deleteUser() {
        editor.remove(USER);
        editor.apply();
    }
}
