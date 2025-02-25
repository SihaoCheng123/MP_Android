package com.example.mealplanner.io.token;

import android.content.Context;
import android.content.SharedPreferences;

public class UserNameManager {
    private static final String PREFS_NAME = "user_data";
    private static final String USER_NAME = "user_name";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserNameManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getUserName() {
        return sharedPreferences.getString(USER_NAME, null);
    }

    public void saveUserName(String username) {
        editor.putString(USER_NAME, username);
        editor.apply();
    }


    public void deleteUserName() {
        editor.remove(USER_NAME);
        editor.apply();
    }

}
