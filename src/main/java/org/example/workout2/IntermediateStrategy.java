package org.example.workout2;

public class IntermediateStrategy implements WeightCalculationStrategy {

    @Override
    public double adjustWeight(double baseWeight) {
        return baseWeight * 0.8;
    }

    @Override
    public int calculateRepetitions() { return 10; }

    @Override
    public int calculateSets() { return 4; }

    @Override
    public int calculatePullUps() { return 10; }

    @Override
    public int calculateCardioMinutes() { return 60; }
}