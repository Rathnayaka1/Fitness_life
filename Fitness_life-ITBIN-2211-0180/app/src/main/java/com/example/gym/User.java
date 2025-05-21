package com.example.gym;

/**
 * Model class representing a user profile with fitness-related data
 */
public class User {
    private String userId;
    private int age;
    private double weight;
    private double height;
    private double bmi;

    // Empty constructor required for Firestore
    public User() {
    }

    public User(int age, double weight, double height) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        calculateBMI();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        calculateBMI();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        calculateBMI();
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    /**
     * Calculate BMI using the formula: weight (kg) / (height (m) * height (m))
     */
    private void calculateBMI() {
        if (height > 0 && weight > 0) {
            bmi = weight / (height * height);
            // Round to 2 decimal places
            bmi = Math.round(bmi * 100.0) / 100.0;
        }
    }
}
