package com.example.fitness_life;

/**
 * Model class for workout plans
 */
public class WorkoutPlan {
    private int id;
    private String firebaseId; // Firestore document ID
    private String name;
    private String description;
    private String difficulty;
    private int duration; // in minutes
    private String workoutTime; // time of day for the workout

    // Default constructor
    public WorkoutPlan() {
    }

    // Constructor with all fields except id
    public WorkoutPlan(String name, String description, String difficulty, int duration, String workoutTime) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.duration = duration;
        this.workoutTime = workoutTime;
    }

    // Constructor with all fields
    public WorkoutPlan(int id, String name, String description, String difficulty, int duration, String workoutTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.duration = duration;
        this.workoutTime = workoutTime;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public String getWorkoutTime() {
        return workoutTime;
    }
    
    public void setWorkoutTime(String workoutTime) {
        this.workoutTime = workoutTime;
    }
}
