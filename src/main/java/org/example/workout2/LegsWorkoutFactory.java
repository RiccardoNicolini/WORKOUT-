package org.example.workout2;

public class LegsWorkoutFactory extends WorkoutFactory {
    @Override
    public Workout createWorkout(User user, WeightCalculationStrategy strategy) {
        Workout workout = new Workout(WorkoutType.LEGS);
        addFundamental(workout, user, strategy, "Squat", 1.2);
        if (user.getLevel() != TrainingLevel.LOW) {
            addIsolation(workout, user, strategy, "Lunges", 0.6);
            addIsolation(workout, user, strategy, "Romanian Deadlift", 0.9);
        }
        return workout;
    }
}