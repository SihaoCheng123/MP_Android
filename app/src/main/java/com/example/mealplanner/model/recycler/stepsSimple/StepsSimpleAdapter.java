package com.example.mealplanner.model.recycler.stepsSimple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.recycler.ingredientsSimple.IngredientSimpleAdapter;

import java.util.ArrayList;

public class StepsSimpleAdapter extends RecyclerView.Adapter<StepsSimpleAdapter.MyViewHolder>{
    Context context;
    ArrayList<StepsSimpleEventModel> stepsList;

    public StepsSimpleAdapter(Context context, ArrayList<StepsSimpleEventModel> stepsList) {
        this.context = context;
        this.stepsList = stepsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_steps_simple, parent, false);
        return new StepsSimpleAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String stepDescription = stepsList.get(position).getStepDescription();
        String stepNumber = stepsList.get(position).getNumberStep();
        holder.stepNumber.setText(stepNumber);
        holder.stepDescription.setText(stepDescription);
    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView stepNumber, stepDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stepNumber = itemView.findViewById(R.id.numberStepCardViewDR);
            stepDescription = itemView.findViewById(R.id.stepDescriptionCardViewDR);
        }
    }

}
