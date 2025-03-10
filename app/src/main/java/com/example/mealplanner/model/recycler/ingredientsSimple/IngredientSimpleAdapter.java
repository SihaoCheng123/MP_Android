package com.example.mealplanner.model.recycler.ingredientsSimple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.data.Ingredients;

import java.util.ArrayList;

public class IngredientSimpleAdapter extends RecyclerView.Adapter<IngredientSimpleAdapter.MyViewHolder> {
    Context context;
    ArrayList<Ingredients> ingredientSimpleList;

    public IngredientSimpleAdapter(Context context, ArrayList<Ingredients> ingredientSimpleList) {
        this.context = context;
        this.ingredientSimpleList = ingredientSimpleList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_ingredients_simple, parent, false);
        return new IngredientSimpleAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ingredients ingredient = ingredientSimpleList.get(position);
        holder.ingredientNameCV.setText(ingredient.getName());
    }

    @Override
    public int getItemCount() {
        return ingredientSimpleList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ingredientNameCV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientNameCV = itemView.findViewById(R.id.ingredientNameSimpleCV);
        }
    }
}
