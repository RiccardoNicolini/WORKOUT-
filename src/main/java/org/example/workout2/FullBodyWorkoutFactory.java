package org.example.workout2;

public class FullBodyWorkoutFactory extends WorkoutFactory {
    @Override
    public Workout createWorkout(User user, WeightCalculationStrategy strategy) {
        Workout workout = new Workout(WorkoutType.FULL_BODY);
        addFundamental(workout, user, strategy, "Bench Press", 1.0);
        addFundamental(workout, user, strategy, "Deadlift", 1.3);
        addFundamental(workout, user, strategy, "Squat", 1.2);
        addBodyweight(workout, user, strategy, "Pull-ups");
        return workout;
    }
}