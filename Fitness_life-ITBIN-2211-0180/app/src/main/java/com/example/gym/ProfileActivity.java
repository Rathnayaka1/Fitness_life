package com.example.gym;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private TextView bmiValue;
    private EditText ageEditText, weightEditText, heightEditText;
    private Button saveButton, deleteButton;
    private ImageButton backButton;
    private FirebaseFirestore db;
    private static final String USER_ID = "current_user"; // Fixed user ID for simplicity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();

        // Initialize UI elements
        ageEditText = findViewById(R.id.ageEditText);
        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        bmiValue = findViewById(R.id.bmiValue);
        saveButton = findViewById(R.id.saveButton);
        deleteButton = findViewById(R.id.deleteButton);
        backButton = findViewById(R.id.backButton);

        // Set click listeners
        saveButton.setOnClickListener(v -> saveUserData());
        deleteButton.setOnClickListener(v -> confirmDeleteProfile());
        backButton.setOnClickListener(v -> onBackPressed());

        // Load user data
        loadUserProfile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload data when activity resumes (e.g., after editing)
        loadUserProfile();
    }

    /**
     * Loads user profile data from Firestore
     */
    private void loadUserProfile() {
        db.collection("users").document(USER_ID)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // Display user data
                        displayUserData(documentSnapshot);
                    } else {
                        Toast.makeText(ProfileActivity.this, "No profile found", Toast.LENGTH_SHORT).show();
                        // If no profile exists, go back to create one
                        finish(); // Simply go back to previous screen
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ProfileActivity.this, "Error loading profile: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * Displays user data from Firestore document
     * @param document Firestore document containing user data
     */
    private void displayUserData(DocumentSnapshot document) {
        if (document != null) {
            // Get data from document
            Long age = document.getLong("age");
            Double weight = document.getDouble("weight");
            Double height = document.getDouble("height");
            Double bmi = document.getDouble("bmi");

            // Display data
            if (age != null) {
                ageEditText.setText(String.valueOf(age));
            }
            
            if (weight != null) {
                weightEditText.setText(String.valueOf(weight));
            }
            
            if (height != null) {
                heightEditText.setText(String.valueOf(height));
            }
            
            if (bmi != null) {
                bmiValue.setText(String.valueOf(bmi));
            }
        }
    }

    /**
     * Saves user data to Firebase Firestore
     */
    private void saveUserData() {
        // Validate inputs
        if (validateInputs()) {
            // Get values from input fields
            int age = Integer.parseInt(ageEditText.getText().toString().trim());
            double weight = Double.parseDouble(weightEditText.getText().toString().trim());
            double height = Double.parseDouble(heightEditText.getText().toString().trim());
            
            // Create user object
            User user = new User(age, weight, height);
            
            // Save to Firestore
            Map<String, Object> userData = new HashMap<>();
            userData.put("age", user.getAge());
            userData.put("weight", user.getWeight());
            userData.put("height", user.getHeight());
            userData.put("bmi", user.getBmi());
            
            db.collection("users").document(USER_ID)
                    .set(userData)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(ProfileActivity.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                        // Update BMI display
                        bmiValue.setText(String.valueOf(user.getBmi()));
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(ProfileActivity.this, "Error updating profile: " + e.getMessage(), 
                                Toast.LENGTH_SHORT).show();
                    });
        }
    }
    
    /**
     * Validates user inputs
     * @return true if all inputs are valid, false otherwise
     */
    private boolean validateInputs() {
        boolean isValid = true;
        
        // Check age
        if (TextUtils.isEmpty(ageEditText.getText())) {
            ageEditText.setError("Please enter your age");
            isValid = false;
        }
        
        // Check weight
        if (TextUtils.isEmpty(weightEditText.getText())) {
            weightEditText.setError("Please enter your weight");
            isValid = false;
        }
        
        // Check height
        if (TextUtils.isEmpty(heightEditText.getText())) {
            heightEditText.setError("Please enter your height");
            isValid = false;
        }
        
        return isValid;
    }

    /**
     * Shows a confirmation dialog before deleting profile
     */
    private void confirmDeleteProfile() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Profile")
                .setMessage("Are you sure you want to delete your profile? This action cannot be undone.")
                .setPositiveButton("Delete", (dialog, which) -> deleteProfile())
                .setNegativeButton("Cancel", null)
                .show();
    }

    /**
     * Deletes user profile from Firestore
     */
    private void deleteProfile() {
        db.collection("users").document(USER_ID)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(ProfileActivity.this, "Profile deleted successfully", Toast.LENGTH_SHORT).show();
                    // Navigate back to main activity
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ProfileActivity.this, "Error deleting profile: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });
    }
}
