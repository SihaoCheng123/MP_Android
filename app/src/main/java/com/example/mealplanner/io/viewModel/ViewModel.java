package com.example.mealplanner.io.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDate;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private final MutableLiveData<LocalDate> selectedDate = new MutableLiveData<>();

    public LiveData<LocalDate> getSelectedDate(){
        return selectedDate;
    }

    public void setSelectedDate(LocalDate newDate){
        selectedDate.setValue(newDate);
    }
}
