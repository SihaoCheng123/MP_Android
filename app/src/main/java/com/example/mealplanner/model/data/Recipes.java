package com.example.mealplanner.model.data;

import java.util.Date;
import java.util.Set;

public class Recipes {
    private Long id;
    private String name;
    private String time;
    private int rations;
    private String date;
    private String image;
    private String category;

    private Set<Steps> steps;

    public Recipes(String name, String time, int rations, String date, String image, String category, Set<Steps> steps) {
        this.name = name;
        this.time = time;
        this.rations = rations;
        this.date = date;
        this.image = image;
        this.category = category;
        this.steps = steps;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRations() {
        return rations;
    }

    public void setRations(int rations) {
        this.rations = rations;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Steps> getSteps() {
        return steps;
    }

    public void setSteps(Set<Steps> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", rations=" + rations +
                ", date='" + date + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", steps=" + steps +
                '}';
    }
}
