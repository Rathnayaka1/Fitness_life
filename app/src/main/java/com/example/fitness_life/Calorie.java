package com.example.fitness_life;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Calorie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie); // Ensure the XML file exists

        // Initialize UI elements
        ImageView menuIcon = findViewById(R.id.menu_icon);
        ImageView profileIcon = findViewById(R.id.profile_icon);
        ImageView caloriesChart = findViewById(R.id.calories_chart);
        TextView moreButton = findViewById(R.id.more_button);

        // Workout section items
        View runningCard = findViewById(R.id.running_card);
        View gymCard = findViewById(R.id.gym_card);
        View cyclingCard = findViewById(R.id.cycling_card);
        View yogaCard = findViewById(R.id.yoga_card);

        // Set click listeners
        if (menuIcon != null) menuIcon.setOnClickListener(v -> showToast("Menu clicked"));
        if (profileIcon != null) profileIcon.setOnClickListener(v -> showToast("Profile clicked"));
        if (caloriesChart != null) caloriesChart.setOnClickListener(v -> showToast("Calories Chart clicked"));
        if (moreButton != null) moreButton.setOnClickListener(v -> showToast("More clicked"));

        if (runningCard != null) runningCard.setOnClickListener(v -> showToast("Running details"));
        if (gymCard != null) gymCard.setOnClickListener(v -> showToast("Gym details"));
        if (cyclingCard != null) cyclingCard.setOnClickListener(v -> showToast("Cycling details"));
        if (yogaCard != null) yogaCard.setOnClickListener(v -> showToast("Yoga details"));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
