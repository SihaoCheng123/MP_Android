package com.example.mealplanner.model.data;

import java.io.Serializable;

public class Ingredients implements Serializable {
    private Long id;
    private String name;

    public Ingredients(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
