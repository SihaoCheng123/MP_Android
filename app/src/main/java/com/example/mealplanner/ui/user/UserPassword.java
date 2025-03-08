 package com.example.mealplanner.ui.user;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.databinding.ActivityUserPasswordBinding;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiUserService;
import com.example.mealplanner.io.token.UserIdManager;
import com.example.mealplanner.model.data.Users;
import com.example.mealplanner.model.dto.PasswordChangeRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class UserPassword extends AppCompatActivity {

    private ActivityUserPasswordBinding binding;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnUpdateUser.setOnClickListener(v -> changePassword());
        binding.iconBackCP.setOnClickListener(v-> finish());
    }

    private void changePassword(){
        ApiUserService apiUserService = ApiClient.getClient().create(ApiUserService.class);
        UserIdManager userIdManager = new UserIdManager(this);
        Call<Users> call = apiUserService.changePassword(getPasswordData(), userIdManager.getUserId());
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Password change successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private PasswordChangeRequest getPasswordData(){
        String userOldPassword = binding.userOldPasswordInputCP.getEditText().getText().toString().trim();
        String userNewPassword = binding.userNewPasswordInputCP.getEditText().getText().toString().trim();
        String confirmPassword = binding.userConfirmNewPasswordInputCP.getEditText().getText().toString().trim();
        PasswordChangeRequest newPasswordRequest = new PasswordChangeRequest();
        if (userOldPassword.isEmpty() && userNewPassword.isEmpty() && confirmPassword.isEmpty()){
            Toast.makeText(this, "Required data", Toast.LENGTH_SHORT).show();
        }else {
            newPasswordRequest.setOldPassword(userOldPassword);
            if (!userNewPassword.equals(confirmPassword)){
                Toast.makeText(this, "New passwords must match", Toast.LENGTH_SHORT).show();
            }else {
                newPasswordRequest.setNewPassword(userNewPassword);
        }
        }
        return newPasswordRequest;
    }
}