package com.example.mealplanner.io.token;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private static final String PREFS_NAME = "user_data";
    private static final String TOKEN_KEY = "jwt_token";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public TokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Obtener el token almacenado
    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    // Guardar el token
    public void saveToken(String token) {
        editor.putString(TOKEN_KEY, token);
        editor.apply();  // Se aplica la edición asincrónicamente
    }

    // Eliminar el token
    public void deleteToken() {
        editor.remove(TOKEN_KEY);
        editor.apply();
    }
}
