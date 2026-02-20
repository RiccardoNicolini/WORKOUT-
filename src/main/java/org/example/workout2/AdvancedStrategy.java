package org.example.workout2;

public class AdvancedStrategy implements WeightCalculationStrategy {

    @Override
    public double adjustWeight(double baseWeight, User user) {
        double factor = 1; // Alto per intermedio

        // Correzione per Sesso
        if (user.getGender().equalsIgnoreCase("Femmina")) {
            factor *= 0.85;
        }

        // Correzione per Altezza (chi è molto alto ha leve più lunghe)
        if (user.getHeight() > 190) factor *= 0.95;

        return baseWeight * factor;
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
