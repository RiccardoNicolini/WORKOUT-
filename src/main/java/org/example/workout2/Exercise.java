package org.example.workout2;

import org.example.workout2.User;

public abstract class Exercise {

    protected String name;
    protected int sets;
    protected int reps;
    protected double weight;

    public Exercise(String name) {
        this.name = name;
    }

    public void generateParameters(User user,
                                   WeightCalculationStrategy strategy) {

        this.sets = strategy.calculateSets();
        this.reps = calculateReps(strategy);
        this.weight = calculateWeight(user, strategy);
    }

    protected int calculateReps(WeightCalculationStrategy strategy) {
        return strategy.calculateRepetitions();
    }

    protected abstract double calculateWeight(User user,
                                              WeightCalculationStrategy strategy);

    public void increaseWeight(double percentage) {
        weight += weight * percentage;
    }

    public void print() {
        System.out.println(name +
                " | Sets: " + sets +
                " | Reps: " + reps +
                " | Weight: " + weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}