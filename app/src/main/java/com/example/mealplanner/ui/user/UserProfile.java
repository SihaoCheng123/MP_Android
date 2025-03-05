package com.example.mealplanner.ui.user;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.databinding.ActivityUserProfileBinding;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiUserService;
import com.example.mealplanner.io.token.UserIdManager;
import com.example.mealplanner.io.token.UserManager;
import com.example.mealplanner.model.data.User_Data;
import com.example.mealplanner.model.data.Users;
import com.example.mealplanner.ui.fragments.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    private ActivityUserProfileBinding binding;
    private UserIdManager userIdManager;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.iconBackUP.setOnClickListener(v-> finish());
        binding.btnUpdateUserUP.setOnClickListener(v-> updateUser(userNewData()));
    }

    public Users userNewData(){
        String userNewName = binding.userNameInputUP.getEditText().getText().toString().trim();
        String userNewEmail =  binding.userEmailInputUP.getEditText().getText().toString().trim();
        String userNewAge = binding.userAgeInputUP.getEditText().getText().toString().trim();
        String userNewPhone = binding.userPhoneInputUP.getEditText().getText().toString().trim();
        userManager = new UserManager(this);
        Users previousUser = userManager.getUser();
        int previousAge = previousUser.getUser_data().getAge();
        Users user = new Users();
        User_Data newUserData = new User_Data();
        if (!userNewName.isEmpty()){
            newUserData.setName(userNewName);
        }
        if (!userNewEmail.isEmpty()){
            user.setEmail(userNewEmail);
        }
        if (!userNewAge.isEmpty()){
            int userAge = Integer.parseInt(userNewAge);
            newUserData.setAge(userAge);
        }else {
            newUserData.setAge(previousAge);
        }
        if (!userNewPhone.isEmpty()){
            newUserData.setPhone(userNewPhone);
        }

        user.setUser_data(newUserData);

        return user;
    }
    public void updateUser(Users users){
        ApiUserService apiUserService = ApiClient.getClient().create(ApiUserService.class);
        userIdManager = new UserIdManager(this);
        userManager = new UserManager(this);
        Log.e("Usuario nuevo enviado", users.toString());
        Call<Users> call = apiUserService.updateUser(users, userIdManager.getUserId());
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    Log.d("Datos de usuario", response.body().toString());
                    Users updatedUser = response.body();
                    userManager.saveUser(updatedUser);
                    Toast.makeText(getApplicationContext(), "Data changed", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Error in changing data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Connection error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}