package org.example.workout2;

public class BeginnerStrategy implements WeightCalculationStrategy {

    @Override
    public double adjustWeight(double baseWeight) {
        return baseWeight * 0.6;
    }

    @Override
    public int calculateRepetitions() { return 15; }

    @Override
    public int calculateSets() { return 3; }

    @Override
    public int calculatePullUps() { return 5; }

    @Override
    public int calculateCardioMinutes() { return 30; }
}
