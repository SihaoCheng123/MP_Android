package com.example.mealplanner.model.recycler.weeklyCalendar;

import static com.example.mealplanner.ui.components.CalendarWeek.selectedDate;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.ui.components.CalendarWeek;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class WeeklyCalendarAdapter extends RecyclerView.Adapter<WeeklyCalendarAdapter.CalendarViewHolder> {

    private final ArrayList<LocalDate> daysOfWeek;
    private final OnItemListener onItemListener;

    public WeeklyCalendarAdapter(ArrayList<LocalDate> daysOfWeek, OnItemListener onItemListener) {
        this.daysOfWeek = daysOfWeek;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_weekly_calendar, parent, false);
        return new CalendarViewHolder(view, onItemListener, daysOfWeek);
    }

    @Override
    public int getItemCount() {
        return daysOfWeek.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        final LocalDate date = daysOfWeek.get(position);
        if (date == null){
            holder.dayNumber.setText("");
            holder.dayText.setText("");
        }else{
            String dayName = date.getDayOfWeek().toString().toUpperCase();
            dayName = dayName.substring(0,2);
            holder.dayText.setText(dayName);
            holder.dayNumber.setText(String.valueOf(date.getDayOfMonth()));
            if (date.equals(selectedDate)){
                holder.parentView.setBackgroundResource(R.drawable.round_card_primary);
                holder.dayText.setTextColor(ContextCompat.getColor(holder.itemView.getContext(),R.color.md_theme_onPrimary));
                holder.dayNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(),R.color.md_theme_onPrimary));
                holder.dayNumber.setPadding(5,0,5,0);
                holder.dayText.setPadding(5,0,5,0);
            }else {
                holder.parentView.setBackgroundColor(Color.TRANSPARENT);
                holder.dayText.setTextColor(ContextCompat.getColor(holder.itemView.getContext(),R.color.md_theme_onBackground));
                holder.dayNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(),R.color.md_theme_onBackground));
            }
        }

    }

    public interface OnItemListener{
        void onItemClick(int position, LocalDate date);
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ArrayList<LocalDate> daysOfWeek;
        public final View parentView;
        final TextView dayText, dayNumber;
        private final WeeklyCalendarAdapter.OnItemListener onItemListener;
        public CalendarViewHolder(@NonNull View itemView, OnItemListener onItemListener, ArrayList<LocalDate> daysOfWeek) {
            super(itemView);
            parentView = itemView.findViewById(R.id.parentViewWC);
            dayNumber = itemView.findViewById(R.id.cardDayNumberText);
            dayText = itemView.findViewById(R.id.cardDayNameText);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
            this.daysOfWeek = daysOfWeek;
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition(), daysOfWeek.get(getAdapterPosition()));
        }
    }
    public static ArrayList<LocalDate> getDaysOfWeek(LocalDate localDate){
        ArrayList<LocalDate> daysOfWeek = new ArrayList<>();
        LocalDate startOfWeek = selectedDate.with(DayOfWeek.SUNDAY);
        for (int i = 0; i < 7; i++){
            daysOfWeek.add(startOfWeek.plusDays(i));
        }
        return daysOfWeek;
    }
}
