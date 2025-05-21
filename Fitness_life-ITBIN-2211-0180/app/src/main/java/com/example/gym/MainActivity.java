package com.example.gym;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText ageInput, weightInput, heightInput;
    private Button saveButton;
    private ImageButton profileButton;
    private FirebaseFirestore db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        
        // Initialize UI elements
        ageInput = findViewById(R.id.ageInput);
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        saveButton = findViewById(R.id.saveButton);
        profileButton = findViewById(R.id.profileButton);
        
        // Set up click listeners
        saveButton.setOnClickListener(v -> saveUserData());
        profileButton.setOnClickListener(v -> openProfileActivity());
    }
    
    /**
     * Saves user data to Firebase Firestore
     */
    private void saveUserData() {
        // Validate inputs
        if (validateInputs()) {
            // Get values from input fields
            int age = Integer.parseInt(ageInput.getText().toString().trim());
            double weight = Double.parseDouble(weightInput.getText().toString().trim());
            double height = Double.parseDouble(heightInput.getText().toString().trim());
            
            // Create user object
            User user = new User(age, weight, height);
            
            // Save to Firestore
            Map<String, Object> userData = new HashMap<>();
            userData.put("age", user.getAge());
            userData.put("weight", user.getWeight());
            userData.put("height", user.getHeight());
            userData.put("bmi", user.getBmi());
            
            // Use a fixed document ID for simplicity (in a real app, you might use user authentication)
            String userId = "current_user";
            
            db.collection("users").document(userId)
                    .set(userData)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(MainActivity.this, "Profile saved successfully!", Toast.LENGTH_SHORT).show();
                        // Clear inputs
                        clearInputs();
                        // Open profile activity
                        openProfileActivity();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(MainActivity.this, "Error saving profile: " + e.getMessage(), 
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
        if (TextUtils.isEmpty(ageInput.getText())) {
            ageInput.setError("Please enter your age");
            isValid = false;
        }
        
        // Check weight
        if (TextUtils.isEmpty(weightInput.getText())) {
            weightInput.setError("Please enter your weight");
            isValid = false;
        }
        
        // Check height
        if (TextUtils.isEmpty(heightInput.getText())) {
            heightInput.setError("Please enter your height");
            isValid = false;
        }
        
        return isValid;
    }
    
    /**
     * Clears all input fields
     */
    private void clearInputs() {
        ageInput.setText("");
        weightInput.setText("");
        heightInput.setText("");
    }
    
    /**
     * Opens the profile activity
     */
    private void openProfileActivity() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}