package com.example.fitness_life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WellcomeScreen extends AppCompatActivity {

    private ImageView imgPage;
    private TextView titlePage, subtitlePage;
    private Button startWorkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome_screen);

        // Initialize views
        imgPage = findViewById(R.id.imgpage);
        titlePage = findViewById(R.id.titlepage);
        subtitlePage = findViewById(R.id.subtitlepage);
        startWorkoutButton = findViewById(R.id.startWorkoutText); // Ensure correct ID

        // Set click listener for the button
        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Sign In screen
                Intent intent = new Intent(WellcomeScreen.this, Sign_In.class);
                startActivity(intent);
                finish(); // Close Welcome Screen to prevent back navigation
            }
        });
    }
}


/*package com.example.fitness_life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WellcomeScreen extends AppCompatActivity {

    private ImageView imgPage;
    private TextView titlePage, subtitlePage;
    private Button startWorkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome_screen);

        // Initialize views
        imgPage = findViewById(R.id.imgpage);
        titlePage = findViewById(R.id.titlepage);
        subtitlePage = findViewById(R.id.subtitlepage);
        startWorkoutButton = findViewById(R.id.startWorkoutText);

        // Set click listener for the button
        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Example action: Navigate to another activity
                Intent intent = new Intent(WellcomeScreen.this, Sign_In.class);
                startActivity(intent);
            }
        });
    }
}
*/