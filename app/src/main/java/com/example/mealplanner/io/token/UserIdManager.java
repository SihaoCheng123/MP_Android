package com.example.mealplanner.io.token;

import android.content.Context;
import android.content.SharedPreferences;

public class UserIdManager {
        final String PREFS_NAME = "user_data";
        private static final String USER_ID = "user_id";
        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;

        public UserIdManager(Context context) {
            sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }

        public Long getUserId() {
            return sharedPreferences.getLong(USER_ID, -1);
        }
        public void saveUserId(Long id) {
            editor.putLong(USER_ID, id);
            editor.apply();
        }

        public void deleteUserId() {
            editor.remove(USER_ID);
            editor.apply();
        }


}
