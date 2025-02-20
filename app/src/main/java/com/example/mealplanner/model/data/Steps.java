package com.example.mealplanner.model.data;

public class Steps {

    private Long id;
    private int number_step;
    private String description;

    public Steps(int number_step, String description) {
        this.number_step = number_step;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber_step() {
        return number_step;
    }

    public void setNumber_step(int number_step) {
        this.number_step = number_step;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Steps{" +
                "number_step=" + number_step +
                ", description='" + description + '\'' +
                '}';
    }
}
