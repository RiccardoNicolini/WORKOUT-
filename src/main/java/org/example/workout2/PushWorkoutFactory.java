package org.example.workout2;

public class PushWorkoutFactory extends WorkoutFactory{
    @Override
    public Workout createWorkout(User user, WeightCalculationStrategy strategy) {
        Workout workout = new Workout(WorkoutType.PUSH);
        addFundamental(workout, user, strategy, "Bench Press", 1.0);
        if (user.getLevel() != TrainingLevel.LOW) {
            addIsolation(workout, user, strategy, "Dumbbell Fly", 0.4);
            addIsolation(workout, user, strategy, "French Press", 0.3);
            addIsolation(workout, user, strategy, "Lateral Raises", 0.25);
        }
        return workout;
    }
}
