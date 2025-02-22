package com.example.mealplanner.ui.components;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ComponentWeeklyCalendarHomeBinding;
import com.example.mealplanner.model.recycler.weeklyCalendar.WeeklyCalendarAdapter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarWeekHS extends Fragment implements WeeklyCalendarAdapter.OnItemListener{
    private ComponentWeeklyCalendarHomeBinding binding;
    private LocalDate selectedDate;

    public CalendarWeekHS() {
        super(R.layout.component_weekly_calendar_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        binding = ComponentWeeklyCalendarHomeBinding.bind(view);
        selectedDate = LocalDate.now();
        setWeekView();
    }

    private void setWeekView() {
        ArrayList<LocalDate> daysInWeek = daysInWeekArray(selectedDate);
        WeeklyCalendarAdapter weeklyCalendarAdapter = new WeeklyCalendarAdapter(daysInWeek, this, selectedDate);
        binding.weeklyCalendarRecyclerHS.setLayoutManager(new GridLayoutManager(getContext(), 7));
        binding.weeklyCalendarRecyclerHS.setAdapter(weeklyCalendarAdapter);
    }

    private ArrayList<LocalDate> daysInWeekArray(LocalDate selectedDate) {
        ArrayList<LocalDate> daysInWeekArray = new ArrayList<>();
        LocalDate current = mondayForDate(selectedDate);
        LocalDate endDate = current.plusWeeks(1);
        while (current.isBefore(endDate)) {
            daysInWeekArray.add(current);
            current = current.plusDays(1);
        }
        return daysInWeekArray;
    }

    private LocalDate mondayForDate(LocalDate current) {
        while (current.getDayOfWeek() != DayOfWeek.MONDAY){
            current = current.minusDays(1);
        }
        return current;
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        selectedDate = date;
        setWeekView();
    }
}
