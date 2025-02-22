package com.example.mealplanner;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mealplanner.io.token.TokenManager;
import com.example.mealplanner.ui.auth.Login;
import com.example.mealplanner.ui.fragments.FragmentManagerWithNav;

public class MainActivity extends AppCompatActivity {

    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tokenManager = new TokenManager(this);

        String token = tokenManager.getToken();
        if (token != null){
            goHomeScreen();
        }else {
            goLogin();
        }
    }

    private void goLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    private void goHomeScreen(){
        Intent intent = new Intent(this, FragmentManagerWithNav.class);
        startActivity(intent);
        finish();
    }
}