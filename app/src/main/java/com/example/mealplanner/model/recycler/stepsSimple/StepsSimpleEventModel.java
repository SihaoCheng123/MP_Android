package com.example.mealplanner.model.recycler.stepsSimple;

public class StepsSimpleEventModel {

    private String numberStep;
    private String stepDescription;

    public StepsSimpleEventModel(String numberStep, String stepDescription) {
        this.numberStep = numberStep;
        this.stepDescription = stepDescription;
    }

    public String getNumberStep() {
        return numberStep;
    }

    public String getStepDescription() {
        return stepDescription;
    }
}
