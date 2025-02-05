package com.example.mealplanner.model.recycler.ingredientsComplex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder> {
    Context context;
    ArrayList<IngredientEventModel> ingredientsList;

    public IngredientAdapter(Context context, ArrayList<IngredientEventModel> ingredientsList) {
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
        String ingredientName = ingredientsList.get(position).getIngredientName();
        String ingredientDetail = ingredientsList.get(position).getIngredientDetail();
        String ingredientPrice = ingredientsList.get(position).getIngredientPrice();

        holder.ingredientNameCV.setText(ingredientName);
        holder.ingredientDetailCV.setText(ingredientDetail);
        holder.ingredientPriceCV.setText(ingredientPrice);
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ingredientNameCV, ingredientDetailCV, ingredientPriceCV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientNameCV = itemView.findViewById(R.id.ingredientNameCV);
            ingredientDetailCV = itemView.findViewById(R.id.ingredientDetailCV);
            ingredientPriceCV = itemView.findViewById(R.id.ingredientPriceCV);
        }
    }
}
