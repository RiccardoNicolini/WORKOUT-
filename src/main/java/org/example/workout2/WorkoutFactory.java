package org.example.workout2;

import org.example.workout2.WorkoutType;

public class WorkoutFactory {

    public static Workout createWorkout(WorkoutType type,
                                        User user,
                                        WeightCalculationStrategy strategy) {

        Workout workout = new Workout(type);

        switch (type) {

            case FULL_BODY:
                addFundamentals(workout, user, strategy);
                break;

            case PUSH:
                addPush(workout, user, strategy);
                break;

            case PULL:
                addPull(workout, user, strategy);
                break;

            case LEGS:
                addLegs(workout, user, strategy);
                break;

            case CARDIO:
                Exercise cardio = new BodyweightExercise("Treadmill");
                cardio.generateParameters(user, strategy);
                workout.addExercise(cardio);
                break;
        }

        return workout;
    }

    private static void addFundamentals(Workout w, User u, WeightCalculationStrategy s) {

        addFundamental(w, u, s, "Bench Press", 1.0);
        addFundamental(w, u, s, "Deadlift", 1.3);
        addFundamental(w, u, s, "Squat", 1.2);

        Exercise pullups = new BodyweightExercise("Pull-ups");
        pullups.generateParameters(u, s);
        w.addExercise(pullups);
    }

    private static void addPush(Workout w, User u, WeightCalculationStrategy s) {

        addFundamental(w, u, s, "Bench Press", 1.0);
        addIsolation(w, u, s, "Dumbbell Fly", 0.4);
        addIsolation(w, u, s, "French Press", 0.3);
        addIsolation(w, u, s, "Lateral Raises", 0.25);
    }

    private static void addPull(Workout w, User u, WeightCalculationStrategy s) {

        addFundamental(w, u, s, "Deadlift", 1.3);

        Exercise pullups = new BodyweightExercise("Pull-ups");
        pullups.generateParameters(u, s);
        w.addExercise(pullups);

        addIsolation(w, u, s, "Curl", 0.3);
        addIsolation(w, u, s, "Hammer Curl", 0.35);
    }

    private static void addLegs(Workout w, User u, WeightCalculationStrategy s) {

        addFundamental(w, u, s, "Squat", 1.2);
        addIsolation(w, u, s, "Lunges", 0.6);
        addIsolation(w, u, s, "Romanian Deadlift", 0.9);
    }

    private static void addFundamental(Workout w, User u,
                                       WeightCalculationStrategy s,
                                       String name,
                                       double multiplier) {

        Exercise ex = new FundamentalExercise(name, multiplier);
        ex.generateParameters(u, s);
        w.addExercise(ex);
    }

    private static void addIsolation(Workout w, User u,
                                     WeightCalculationStrategy s,
                                     String name,
                                     double multiplier) {

        Exercise ex = new IsolationExercise(name, multiplier);
        ex.generateParameters(u, s);
        w.addExercise(ex);
    }
}

