package org.example.workout2;

import org.example.workout2.WorkoutType;

import java.util.ArrayList;
import java.util.List;

public class Workout {

    private WorkoutType type;
    private List<Exercise> exercises;
    private boolean completed;

    public Workout(WorkoutType type) {
        this.type = type;
        this.exercises = new ArrayList<>();
        this.completed = false;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void markAsCompleted(boolean value) {
        this.completed = value;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void increaseIntensity() {
        exercises.forEach(e -> e.increaseWeight(0.05));
    }

    public WorkoutType getType() {
        return type;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}