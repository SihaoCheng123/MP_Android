package com.example.mealplanner.model.recycler.ingredientsComplex;

public class IngredientEventModel {
    public String ingredientName;
    public String ingredientDetail;
    public String ingredientPrice;

    public IngredientEventModel(String ingredientName, String ingredientDetail, String ingredientPrice) {
        this.ingredientName = ingredientName;
        this.ingredientDetail = ingredientDetail;
        this.ingredientPrice = ingredientPrice;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getIngredientDetail() {
        return ingredientDetail;
    }

    public String getIngredientPrice() {
        return ingredientPrice;
    }
}
