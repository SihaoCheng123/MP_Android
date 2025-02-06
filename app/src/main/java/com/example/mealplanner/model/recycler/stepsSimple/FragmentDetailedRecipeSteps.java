package com.example.mealplanner.model.recycler.stepsSimple;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;

import java.util.ArrayList;

public class FragmentDetailedRecipeSteps extends Fragment {

    private StepsSimpleAdapter adapter;
    private ArrayList<StepsSimpleEventModel> stepsList = new ArrayList<>();

    public FragmentDetailedRecipeSteps() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed_recipe_steps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        setStepsEventModels();
        adapter = new StepsSimpleAdapter(view.getContext(), stepsList);
        RecyclerView recyclerView = view.findViewById(R.id.stepsRecyclerDR);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setStepsEventModels(){
        String [] stepsDescriptions = getResources().getStringArray(R.array.step_description_cv);
        for (int i = 0; i < stepsDescriptions.length; i++){
            stepsList.add(new StepsSimpleEventModel(String.valueOf(i+1), stepsDescriptions[i]));
        }
    }

}