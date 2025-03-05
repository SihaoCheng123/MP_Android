package com.example.mealplanner.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityUserDataProfileBinding;
import com.example.mealplanner.databinding.ActivityUserProfileBinding;
import com.example.mealplanner.io.token.UserManager;
import com.example.mealplanner.model.data.Users;

public class UserDataProfile extends AppCompatActivity {

    ActivityUserDataProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserDataProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.editUserBtnUDP.setOnClickListener(v->goEditProfileScreen());
        binding.iconBackUP.setOnClickListener(v -> finish());
    }

    protected void onResume(){
        super.onResume();
        loadUserData();
    }

    private void loadUserData(){
        UserManager userManager = new UserManager(this);
        Log.e("Usuario guardado", userManager.getUser().toString());
        Users user = userManager.getUser();
        binding.userActualNameUDP.setHint(user.getUser_data().getName());
        binding.userActualAgeUDP.setHint(String.valueOf(user.getUser_data().getAge()));
        binding.userActualEmailUDP.setHint(user.getEmail());
        binding.userActualPhoneUDP.setHint(user.getUser_data().getPhone());
    }

    public void goEditProfileScreen(){
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }
}