package org.example.workout2;

public class PullWorkoutFactory extends WorkoutFactory {
    @Override
    public Workout createWorkout(User user, WeightCalculationStrategy strategy) {
        Workout workout = new Workout(WorkoutType.PULL);
        addFundamental(workout, user, strategy, "Deadlift", 1.3);
        addBodyweight(workout, user, strategy, "Pull-ups");
        if (user.getLevel() != TrainingLevel.LOW) {
            addIsolation(workout, user, strategy, "Curl", 0.3);
            addIsolation(workout, user, strategy, "Hammer Curl", 0.35);
        }
        return workout;
    }
}
