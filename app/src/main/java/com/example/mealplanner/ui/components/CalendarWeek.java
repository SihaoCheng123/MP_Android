package com.example.mealplanner.ui.components;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mealplanner.R;
import com.example.mealplanner.databinding.ComponentWeeklyCalendarBinding;
import com.example.mealplanner.model.recycler.weeklyCalendar.WeeklyCalendarAdapter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarWeek extends Fragment implements WeeklyCalendarAdapter.OnItemListener{
    ComponentWeeklyCalendarBinding binding;
    final String [] monthsList = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static LocalDate selectedDate;

    public CalendarWeek() {
        super(R.layout.component_weekly_calendar);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        binding = ComponentWeeklyCalendarBinding.bind(view);
        selectedDate = LocalDate.now();
        setWeekView();
        binding.iconArrowBackWC.setOnClickListener(v -> previousAction());
        binding.iconArrowForwardWC.setOnClickListener(v -> nextAction());
    }

//    @Override
//    protected void onCreate(Bundle savedInstance){
//        super.onCreate(savedInstance);
//        binding = ComponentWeeklyCalendarBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        selectedDate = LocalDate.now();
//    }

    private void setWeekView(){
        binding.monthTextWC.setText(selectedDate.getMonth().toString());
        binding.yearTextWC.setText(String.valueOf(selectedDate.getYear()));
        ArrayList<LocalDate> dayTextInWeek = WeeklyCalendarAdapter.getDaysOfWeek(selectedDate);
        ArrayList<LocalDate> daysInWeek = daysInWeekArray(selectedDate);
        WeeklyCalendarAdapter weeklyCalendarAdapter = new WeeklyCalendarAdapter(daysInWeek, this);
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

    private ArrayList<LocalDate> daysInMonthArray(LocalDate date){
        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        for (int i = 1; i <= 42 ; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add(null);
            }
            else {
                daysInMonthArray.add(LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), i - dayOfWeek));
            }
        }
        return daysInMonthArray;
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
