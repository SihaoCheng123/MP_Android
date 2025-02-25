package com.example.mealplanner.model.recycler.ingredientsComplex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.data.Ingredients;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder> {
    Context context;
    List<Ingredients> ingredientsList;

    public IngredientAdapter(Context context, List<Ingredients> ingredientsList) {
        this.context = context;
        this.ingredientsList = ingredientsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_ingredient_complex, parent, false);
        return new IngredientAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String ingredientName = ingredientsList.get(position).getName();
        holder.ingredientNameCV.setText(ingredientName);
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ingredientNameCV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientNameCV = itemView.findViewById(R.id.ingredientNameCV);
        }
    }
}
