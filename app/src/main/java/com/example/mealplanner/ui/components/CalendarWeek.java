package com.example.mealplanner.ui.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ComponentWeeklyCalendarBinding;
import com.example.mealplanner.io.viewModel.ViewModel;
import com.example.mealplanner.model.recycler.weeklyCalendar.WeeklyCalendarAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarWeek extends Fragment implements WeeklyCalendarAdapter.OnItemListener{
    ComponentWeeklyCalendarBinding binding;
    public LocalDate selectedDate;
    private ViewModel dateViewModel;
    private LocalDate selectedFromCalendar;
    private WeeklyCalendarAdapter weeklyCalendarAdapter;

    public CalendarWeek() {
        super(R.layout.component_weekly_calendar);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        binding = ComponentWeeklyCalendarBinding.bind(view);
        dateViewModel = new ViewModelProvider(requireParentFragment()).get(ViewModel.class);
        dateViewModel.getSelectedDate().observe(getViewLifecycleOwner(), newDate-> {
            selectedDate = newDate;
            setWeekView();
        });
        if (selectedDate == null){
            selectedDate = LocalDate.now();
            dateViewModel.setSelectedDate(selectedDate);
        }
        setWeekView();
        binding.iconArrowBackWC.setOnClickListener(v -> previousAction());
        binding.iconArrowForwardWC.setOnClickListener(v -> nextAction());

        binding.monthTextWC.setOnClickListener(v -> showCalendar());
    }


    private void setWeekView(){
        binding.monthTextWC.setText(selectedDate.getMonth().toString());
        binding.yearTextWC.setText(String.valueOf(selectedDate.getYear()));
        ArrayList<LocalDate> daysInWeek = daysInWeekArray(selectedDate);
        weeklyCalendarAdapter = new WeeklyCalendarAdapter(daysInWeek, this, selectedDate);
        binding.weeklyCalendarRecycler.setLayoutManager(new GridLayoutManager(getContext(), 7));
        binding.weeklyCalendarRecycler.setAdapter(weeklyCalendarAdapter);
    }

    private ArrayList<LocalDate> daysInWeekArray(LocalDate selectedDate) {
        ArrayList<LocalDate> daysInWeekArray = new ArrayList<>();
        LocalDate current = sundayForDate(selectedDate);
        LocalDate endDate = current.plusWeeks(1);
        while (current.isBefore(endDate)){
            daysInWeekArray.add(current);
            current = current.plusDays(1);
        }
        return  daysInWeekArray;
    }

    private LocalDate sundayForDate(LocalDate current) {
        LocalDate onwWeekAgo = current.minusWeeks(1);
        while (current.isAfter(onwWeekAgo)) {
            if (current.getDayOfWeek() == DayOfWeek.MONDAY){
                return  current;
            }
            current = current.minusDays(1);
        }
        return null;
    }


    @Override
    public void onItemClick(int position, LocalDate date) {
        selectedDate = date;
        dateViewModel.setSelectedDate(selectedDate);
    }

    public void previousAction(){
        selectedDate = selectedDate.minusWeeks(1);
        dateViewModel.setSelectedDate(selectedDate);
    }

    public void nextAction(){
        selectedDate = selectedDate.plusWeeks(1);
        dateViewModel.setSelectedDate(selectedDate);
    }

    private void showCalendar(){
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        LayoutInflater inflater = getLayoutInflater();
        View calendarView = inflater.inflate(R.layout.activity_calendar, null);
        CalendarView calendar = calendarView.findViewById(R.id.calendarView);
        dateViewModel = new ViewModelProvider(requireParentFragment()).get(ViewModel.class);
        calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedFromCalendar = LocalDate.of(year, month +1, dayOfMonth);
        });

        alertDialogBuilder.setTitle("Calendar")
                .setPositiveButton("OK", ((dialog, which) -> {
                    dateViewModel.setSelectedDate(selectedFromCalendar);
                    setWeekView();
                }))
                .setNegativeButton("Cancel",((dialog, which) -> {}));
        alertDialogBuilder.setView(calendarView);
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }


}
