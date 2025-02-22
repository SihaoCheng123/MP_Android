package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.io.api.ApiClient;
import com.example.mealplanner.io.api.ApiRecipeService;
import com.example.mealplanner.io.viewModel.ViewModel;
import com.example.mealplanner.model.data.Recipes;
import com.example.mealplanner.ui.components.CalendarWeek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Calendar extends Fragment {

    private LocalDate selectedDate;
    private ViewModel dateViewModel;

    public Calendar() {
        super(R.layout.fragment_calendar);
    }

    public static Calendar newInstance() {
        Calendar fragment = new Calendar();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dateViewModel = new ViewModelProvider(this).get(ViewModel.class);
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        if (savedInstanceState == null){
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.calendarContainerCS, new CalendarWeek());
            transaction.commit();
        }
        selectedDate = LocalDate.now();
        dateViewModel.getSelectedDate().observe(getViewLifecycleOwner(), newDate->{
            getRecipeByDate(formatDate(newDate));
        });

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private String formatDate(LocalDate selectedDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(selectedDate);
    }

    private void getRecipeByDate(String date){
        ApiRecipeService apiRecipeService = ApiClient.getClient().create(ApiRecipeService.class);
        Call<List<Recipes>> call = apiRecipeService.getRecipesByDate(date);
        call.enqueue(new Callback<List<Recipes>>() {
            @Override
            public void onResponse(Call<List<Recipes>> call, Response<List<Recipes>> response) {
                if (response.isSuccessful()){
                    for (Recipes recipe: response.body()) {
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