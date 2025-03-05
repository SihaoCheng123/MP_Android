package com.example.mealplanner.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.databinding.FragmentUserBinding;
import com.example.mealplanner.io.token.UserManager;
import com.example.mealplanner.model.data.Users;
import com.example.mealplanner.ui.user.UserDataProfile;
import com.example.mealplanner.ui.user.UserFavRecipes;
import com.example.mealplanner.ui.user.UserPassword;
import com.example.mealplanner.ui.user.UserProfile;
import com.example.mealplanner.ui.user.UserRecipes;
import com.example.mealplanner.ui.user.UserSettings;

public class User extends Fragment {

    private UserManager userManager;
    private FragmentUserBinding binding;
    public User() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false);
        binding.userProfileUS.setOnClickListener(v -> goUserProfile());
        binding.favRecipesUS.setOnClickListener(v -> goFavRecipes());
        binding.savedRecipesUS.setOnClickListener(v -> goPrepRecipes());
        binding.changePasswordUS.setOnClickListener(v -> goChangePassword());
        binding.settingsUS.setOnClickListener(v -> goSettings());
        userManager = new UserManager(container.getContext());
        Users user = userManager.getUser();
        binding.usernameUS.setText(user.getUser_data().getName());
        return binding.getRoot();
    }

    public void goUserProfile(){
        Intent intent = new Intent(getActivity(), UserDataProfile.class);
        startActivity(intent);
    }
    public void goPrepRecipes(){
        Intent intent = new Intent(getActivity(), UserRecipes.class);
        startActivity(intent);
    }
    public void goFavRecipes(){
        Intent intent = new Intent(getActivity(), UserFavRecipes.class);
        startActivity(intent);
    }
    public void goChangePassword(){
        Intent intent = new Intent(getActivity(), UserPassword.class);
        startActivity(intent);
    }

    public void goSettings(){
        Intent intent = new Intent(getActivity(), UserSettings.class);
        startActivity(intent);
    }
}