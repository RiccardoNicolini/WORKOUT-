package org.example.workout2;

import org.example.workout2.WorkoutType;

public class WorkoutFactory {

    public static Workout createWorkout(WorkoutType type, User user, WeightCalculationStrategy strategy) {
        Workout workout = new Workout(type);
        TrainingLevel livello = user.getLevel();

        switch (type) {
            case PUSH -> {
                addFundamental(workout, user, strategy, "Bench Press", 1.0);
                if (livello != TrainingLevel.LOW) {
                    addIsolation(workout, user, strategy, "Dumbbell Fly", 0.4);
                    addIsolation(workout, user, strategy, "French Press", 0.3);
                    addIsolation(workout, user, strategy, "Lateral Raises", 0.25);
                }
            }
            case PULL -> {
                addFundamental(workout, user, strategy, "Deadlift", 1.3);
                addBodyweight(workout, user, strategy, "Pull-ups");
                if (livello != TrainingLevel.LOW) {
                    addIsolation(workout, user, strategy, "Curl", 0.3);
                    addIsolation(workout, user, strategy, "Hammer Curl", 0.35);
                }
            }
            case LEGS -> {
                addFundamental(workout, user, strategy, "Squat", 1.2);
                if (livello != TrainingLevel.LOW) {
                    addIsolation(workout, user, strategy, "Lunges", 0.6);
                    addIsolation(workout, user, strategy, "Romanian Deadlift", 0.9);
                }
            }
            case FULL_BODY -> {
                addFundamental(workout, user, strategy, "Bench Press", 1.0);
                addFundamental(workout, user, strategy, "Deadlift", 1.3);
                addFundamental(workout, user, strategy, "Squat", 1.2);
                addBodyweight(workout, user, strategy, "Pull-ups");
            }
            case CARDIO -> {
                Exercise cardio = new BodyweightExercise("Treadmill (Running)");
                cardio.generateParameters(user, strategy);
                workout.addExercise(cardio);
            }
        }
        return workout;
    }

    private static void addFundamental(Workout w, User u, WeightCalculationStrategy s, String n, double m) {
        Exercise ex = new FundamentalExercise(n, m);
        ex.generateParameters(u, s);
        w.addExercise(ex);
    }

    private static void addIsolation(Workout w, User u, WeightCalculationStrategy s, String n, double m) {
        Exercise ex = new IsolationExercise(n, m);
        ex.generateParameters(u, s);
        w.addExercise(ex);
    }

    private static void addBodyweight(Workout w, User u, WeightCalculationStrategy s, String n) {
        Exercise ex = new BodyweightExercise(n);
        ex.generateParameters(u, s);
        w.addExercise(ex);
    }
}