package org.example.workout2;

import org.example.workout2.Exercise;
import org.example.workout2.User;
import org.example.workout2.WeightCalculationStrategy;

public class BodyweightExercise extends Exercise {

    public BodyweightExercise(String name) {
        super(name);
    }

    @Override
    protected double calculateWeight(User user,
                                     WeightCalculationStrategy strategy) {

        return 0; // corpo libero
    }

    @Override
    protected int calculateReps(WeightCalculationStrategy strategy) {
        return strategy.calculatePullUps();
    }
}
