package com.example.mealplanner.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.databinding.ActivityUserSettingsBinding;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiUserService;
import com.example.mealplanner.io.token.TokenManager;
import com.example.mealplanner.io.token.UserIdManager;
import com.example.mealplanner.io.token.UserManager;
import com.example.mealplanner.ui.auth.Login;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSettings extends AppCompatActivity {
    private ActivityUserSettingsBinding binding;
    private UserIdManager userIdManager;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iconBackUS.setOnClickListener(v -> finish());
        binding.logOutBtnUS.setOnClickListener(v -> logOut());
        binding.deleteBtnUS.setOnClickListener(v -> deleteAccount());
        binding.notificationSwitchUS.setOnCheckedChangeListener(((buttonView, isChecked) ->{
            if (isChecked){
                Toast.makeText(getApplicationContext(), "Notifications activated",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "Notifications deactivated",Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void logOut(){
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setTitle("Log out")
                        .setMessage("Do you really want to log out?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                                    deleteData();
                                    goLogin();})
                        .setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        alertDialogBuilder.show();
    }

    private void deleteAccount(){
        userIdManager = new UserIdManager(this);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setTitle("Delete account")
                .setMessage("Are you sure about this?")
                .setPositiveButton("Yes", (dialog, which) -> deleteAccountFromDB(userIdManager.getUserId()))
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        alertDialogBuilder.show();
    }

    private void deleteAccountFromDB(Long user_id){
        ApiUserService apiUserService = ApiClient.getClient().create(ApiUserService.class);
        Call<Void> call = apiUserService.deleteUser(user_id);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Account deleted successfully", Toast.LENGTH_SHORT).show();
                    deleteData();
                    goLogin();
                } else {
                    Toast.makeText(getApplicationContext(), "Error deleting account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Connection error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void deleteData(){
        tokenManager = new TokenManager(this);
        tokenManager.deleteToken();
        userIdManager = new UserIdManager(this);
        UserManager userManager = new UserManager(this);
        userIdManager.deleteUserId();
        userManager.deleteUser();
    }
}