package org.example.workout2;

import org.example.workout2.WorkoutType;

public abstract class WorkoutFactory {

    // Il Factory Method che ogni sottoclasse deve implementare
    public abstract Workout createWorkout(User user, WeightCalculationStrategy strategy);

    // Metodi helper definiti nella classe base per essere usati dalle sottoclassi
    protected void addFundamental(Workout w, User u, WeightCalculationStrategy s, String n, double m) {
        Exercise ex = new FundamentalExercise(n, m);
        ex.generateParameters(u, s);
        w.addExercise(ex);
    }

    protected void addIsolation(Workout w, User u, WeightCalculationStrategy s, String n, double m) {
        Exercise ex = new IsolationExercise(n, m);
        ex.generateParameters(u, s);
        w.addExercise(ex);
    }

    protected void addBodyweight(Workout w, User u, WeightCalculationStrategy s, String n) {
        Exercise ex = new BodyweightExercise(n);
        ex.generateParameters(u, s);
        w.addExercise(ex);
    }
}