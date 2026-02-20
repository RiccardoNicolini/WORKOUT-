package org.example.workout2;

public interface WeightCalculationStrategy {

    double adjustWeight(double baseWeight);

    int calculateRepetitions();

    int calculateSets();

    int calculatePullUps();

    int calculateCardioMinutes();
}
