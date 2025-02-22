package com.example.mealplanner.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.FragmentHomeScreenBinding;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiRecipeService;
import com.example.mealplanner.io.viewModel.ViewModel;
import com.example.mealplanner.model.data.Recipes;
import com.example.mealplanner.ui.components.AddRecipe;
import com.example.mealplanner.ui.components.CalendarWeek;
import com.example.mealplanner.ui.components.CalendarWeekHS;
import com.example.mealplanner.ui.components.RecipeDetailed;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeScreen extends Fragment {


    private LocalDate selectedDate;
    private ViewModel dateViewModel;

    public HomeScreen() {
        // Required empty public constructor
    }

    public static HomeScreen newInstance(String param1, String param2) {
        HomeScreen fragment = new HomeScreen();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        dateViewModel = new ViewModelProvider(this).get(ViewModel.class);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.calendarContainerHS, new CalendarWeekHS());
            transaction.commit();
        }
        selectedDate = LocalDate.now();
        dateViewModel.getSelectedDate().observe(getViewLifecycleOwner(), newDate->{
            getRecipeByDate(formatDate(newDate));
        });
        // Inflate the layout for this fragment
        TextView card_breakfast = (TextView) view.findViewById(R.id.card_breakfast);
        TextView card_lunch = (TextView) view.findViewById(R.id.card_lunch);
        TextView card_dinner = (TextView) view.findViewById(R.id.card_dinner);
        card_breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), RecipeDetailed.class);
                startActivity(intent);
            }
        });
        card_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AddRecipe.class);
                startActivity(intent);
            }
        });
        card_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AddRecipe.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private String formatDate(LocalDate selectedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(selectedDate);
    }

    private void getRecipeByDate(String date) {
        ApiRecipeService apiRecipeService = ApiClient.getClient().create(ApiRecipeService.class);
        Call<List<Recipes>> call = apiRecipeService.getRecipesByDate(date);
        call.enqueue(new Callback<List<Recipes>>() {
            @Override
            public void onResponse(Call<List<Recipes>> call, Response<List<Recipes>> response) {
                if (response.isSuccessful()) {
                    for (Recipes recipe : response.body()) {
                        Log.e("response", recipe.getName());
                    }
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Recipes>> call, Throwable throwable) {
                Toast.makeText(getContext(), "Error trayendo receta", Toast.LENGTH_SHORT).show();
            }
        });
    }
}