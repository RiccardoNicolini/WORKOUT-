package org.example.workout2;

public class CardioWorkoutFactory extends WorkoutFactory {
    @Override
    public Workout createWorkout(User user, WeightCalculationStrategy strategy) {
        Workout workout = new Workout(WorkoutType.CARDIO);
        Exercise cardio = new BodyweightExercise("Treadmill (Running)");
        cardio.generateParameters(user, strategy);
        workout.addExercise(cardio);
        return workout;
    }
}