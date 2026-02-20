package org.example.workout2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkoutPlan {

    private List<Workout> weeklyWorkouts;

    public WorkoutPlan() {
        weeklyWorkouts = new ArrayList<>();
    }

    public void addWorkout(Workout workout) {
        weeklyWorkouts.add(workout);
    }

    public void displayWeeklyPlan() {

        System.out.println("=========== WEEKLY WORKOUT PLAN ===========");

        for (int i = 0; i < weeklyWorkouts.size(); i++) {

            Workout w = weeklyWorkouts.get(i);

            System.out.println("\nWorkout " + (i + 1) + " - " + w.getType());

            for (Exercise e : w.getExercises()) {
                e.print();
            }

            System.out.println("Completed: " + w.isCompleted());
        }
    }

    public void askCompletionFromUser() {

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < weeklyWorkouts.size(); i++) {

            System.out.println("Did you complete Workout "
                    + (i + 1) + " ? (yes/no)");

            String input = scanner.nextLine();

            boolean completed = input.equalsIgnoreCase("yes");

            weeklyWorkouts.get(i).markAsCompleted(completed);
        }
    }

    public void evaluateProgression() {

        boolean allCompleted =
                weeklyWorkouts.stream()
                        .allMatch(Workout::isCompleted);

        if (allCompleted) {

            System.out.println("All workouts completed! Increasing intensity...");

            weeklyWorkouts.forEach(Workout::increaseIntensity);

        } else {

            System.out.println("Not all workouts completed. Intensity unchanged.");
        }
    }

    public List<Workout> getWeeklyWorkouts() {
        return weeklyWorkouts;
    }

}