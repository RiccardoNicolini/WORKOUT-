package org.example.workout2;

public class AdvancedStrategy implements WeightCalculationStrategy {

    @Override
    public double adjustWeight(double baseWeight) {
        return baseWeight * 1.0;
    }

    @Override
    public int calculateRepetitions() { return 5; }

    @Override
    public int calculateSets() { return 5; }

    @Override
    public int calculatePullUps() { return 15; }

    @Override
    public int calculateCardioMinutes() { return 120; }
}
