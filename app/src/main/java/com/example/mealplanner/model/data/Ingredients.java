package com.example.mealplanner.model.data;

public class Ingredients {
    private Long id;
    private String name;

    public Ingredients(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
