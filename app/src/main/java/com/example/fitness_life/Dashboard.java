package com.example.fitness_life;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    private Button activityButton, heartButton, nutritionButton, scheduleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); // Ensure the XML file is correctly named

        // Initialize buttons
        activityButton = findViewById(R.id.activity_button);
        heartButton = findViewById(R.id.heart_button);
        nutritionButton = findViewById(R.id.nutrition_button);
        scheduleButton = findViewById(R.id.schedule_button);

        // Set Click Listeners
        activityButton.setOnClickListener(view -> showToast("Activity Selection Clicked"));
        heartButton.setOnClickListener(view -> showToast("Heart Rate Monitor Clicked"));
        nutritionButton.setOnClickListener(view -> showToast("Nutrition Selection Clicked"));
        scheduleButton.setOnClickListener(view -> showToast("Schedule Clicked"));
    }

    // Method to display a simple toast message
    private void showToast(String message) {
        Toast.makeText(Dashboard.this, message, Toast.LENGTH_SHORT).show();
    }
}
