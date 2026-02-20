package org.example.workout2;

import org.example.workout2.User;

public class FundamentalExercise extends Exercise {

    private double bodyweightMultiplier;

    public FundamentalExercise(String name,
                               double bodyweightMultiplier) {
        super(name);
        this.bodyweightMultiplier = bodyweightMultiplier;
    }

    @Override
    protected double calculateWeight(User user, WeightCalculationStrategy strategy) {
        double base = user.getWeight() * bodyweightMultiplier;

        return strategy.adjustWeight(base, user);
    }
}