package com.example.mealplanner.ui.components;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.model.data.Steps;
import com.example.mealplanner.model.recycler.stepsSimple.StepsSimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FragmentDetailedRecipeSteps extends Fragment {

    private ArrayList<Steps> stepsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailed_recipe_steps, container, false);
        Bundle bundle = getArguments();
        if (bundle != null){
            stepsList = (ArrayList<Steps>) bundle.getSerializable("stepsList");
        }
        if (stepsList == null || stepsList.isEmpty()){
            Toast.makeText(getContext(), "No steps found", Toast.LENGTH_SHORT).show();
        }else {
            Collections.sort(stepsList, new Comparator<Steps>() {
                @Override
                public int compare(Steps o1, Steps o2) {
                    return Integer.compare(o1.getNumber_step(), o2.getNumber_step());
                }
            });
            StepsSimpleAdapter adapter = new StepsSimpleAdapter(view.getContext(), stepsList);
            RecyclerView recyclerView = view.findViewById(R.id.stepsRecyclerDR);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

    }


}