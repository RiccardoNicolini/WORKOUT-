package org.example.workout2;

import org.example.workout2.Exercise;
import org.example.workout2.User;
import org.example.workout2.WeightCalculationStrategy;

public class IsolationExercise extends Exercise {

    private double bodyweightMultiplier;

    public IsolationExercise(String name,
                             double bodyweightMultiplier) {
        super(name);
        this.bodyweightMultiplier = bodyweightMultiplier;
    }

    @Override
    protected double calculateWeight(User user,
                                     WeightCalculationStrategy strategy) {

        double base = user.getWeight() * bodyweightMultiplier;

        return strategy.adjustWeight(base);
    }
}