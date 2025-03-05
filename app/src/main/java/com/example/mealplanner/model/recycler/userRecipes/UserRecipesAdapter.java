package com.example.mealplanner.model.recycler.userRecipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.data.Ingredients;
import com.example.mealplanner.model.data.Recipes;

import java.util.ArrayList;
import java.util.Set;

public class UserRecipesAdapter extends RecyclerView.Adapter<UserRecipesAdapter.MyViewHolder> {

    Context context;
    ArrayList<Recipes> recipesList;

    public UserRecipesAdapter(Context context, ArrayList<Recipes> recipesList){
        this.context = context;
        this.recipesList = recipesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_recipe, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Recipes recipes = recipesList.get(position);
        holder.itemView.setBackgroundResource(R.drawable.round_card_white);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
        layoutParams.bottomMargin = 40;
        holder.itemView.setLayoutParams(layoutParams);
        holder.recipeName.setText(recipes.getName());
        Set<Ingredients> ingredients = recipes.getIngredients();
        int cont = 0;
        for(Ingredients ingredient : ingredients){
            if (cont == 0){
                holder.ing1.setText(ingredient.getName());
            } else if (cont == 1) {
                holder.ing2.setText(ingredient.getName());
            }else if (cont == 2){
                holder.ing3.setText(ingredient.getName());
            }
            cont++;
        }
    }

    @Override
    public int getItemCount(){
        return recipesList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView recipeName, ing1, ing2, ing3;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeName);
            ing1 = itemView.findViewById(R.id.recipeIng1);
            ing2 = itemView.findViewById(R.id.recipeIng2);
            ing3 = itemView.findViewById(R.id.recipeIng3);
        }
    }
}
