package com.example.mealplanner.ui.fragments;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ActivityFragmentmanagerwithnavBinding;

public class FragmentManagerWithNav extends AppCompatActivity {

   ActivityFragmentmanagerwithnavBinding binding;
   @Override
   protected  void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       binding = ActivityFragmentmanagerwithnavBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());
       replaceFragment(new HomeScreen());

       binding.bottomNavigationView.setOnItemSelectedListener(item -> {
           if(item.getItemId() == R.id.home) {
               replaceFragment(new HomeScreen());
           } else if (item.getItemId() == R.id.calendar) {
               replaceFragment(new Calendar());
           }else if (item.getItemId() == R.id.cart) {
               replaceFragment(new ShoppingCart());
           }else if (item.getItemId() == R.id.user) {
               replaceFragment(new User());
           }

           return true;
       });
   }

   private void replaceFragment(Fragment fragment){
       FragmentManager fragmentManager = getSupportFragmentManager();
       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       fragmentTransaction.replace(R.id.frameLayout, fragment);
       fragmentTransaction.commit();
   }
}