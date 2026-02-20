package org.example.workout2;

public class User {

    private String gender;
    private double weight;
    private double height;
    private TrainingLevel level;
    private int weeklyFrequency;

    public User(String gender, double weight, double height,
                TrainingLevel level, int weeklyFrequency) {
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.level = level;
        this.weeklyFrequency = weeklyFrequency;
    }

    public String getGender() { return gender; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public TrainingLevel getLevel() { return level; }
    public int getWeeklyFrequency() { return weeklyFrequency; }

    public void updateData(double weight, double height, TrainingLevel level) {
        this.weight = weight;
        this.height = height;
        this.level = level;
    }
}