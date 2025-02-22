package com.example.mealplanner.ui.components;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ComponentWeeklyCalendarBinding;
import com.example.mealplanner.model.recycler.weeklyCalendar.WeeklyCalendarAdapter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarWeek extends Fragment implements WeeklyCalendarAdapter.OnItemListener{
    ComponentWeeklyCalendarBinding binding;
    public static LocalDate selectedDate;

    public CalendarWeek() {
        super(R.layout.component_weekly_calendar);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        binding = ComponentWeeklyCalendarBinding.bind(view);
        selectedDate = LocalDate.now();
        setWeekView();
        binding.iconArrowBackWC.setOnClickListener(v -> previousAction());
        binding.iconArrowForwardWC.setOnClickListener(v -> nextAction());
    }


    private void setWeekView(){
        binding.monthTextWC.setText(selectedDate.getMonth().toString());
        binding.yearTextWC.setText(String.valueOf(selectedDate.getYear()));
        ArrayList<LocalDate> daysInWeek = daysInWeekArray(selectedDate);
        WeeklyCalendarAdapter weeklyCalendarAdapter = new WeeklyCalendarAdapter(daysInWeek, this, selectedDate);
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
        setWeekView();
    }

    public void previousAction(){
        selectedDate = selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextAction(){
        selectedDate = selectedDate.plusWeeks(1);
        setWeekView();
    }
}
