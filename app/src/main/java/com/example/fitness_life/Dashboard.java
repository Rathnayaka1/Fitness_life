package com.example.fitness_life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    private Button button1, button2, button3, button4;
  //  private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); // Ensure correct layout

        // Get Username from Intent
        String username = getIntent().getStringExtra("USERNAME");

        // Initialize Views
       // welcomeText = findViewById(R.id.welcomeText);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        // Set welcome message


        // Button Click Listeners
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashboard.this, "Activity Selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this, TopExercise.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashboard.this, "Heart rate selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this, Heartrate.class));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashboard.this, "Nutrition Selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this, Nutrition.class));
            }
        });

        button4.setOnClickListener(v -> {
            Toast.makeText(Dashboard.this, "Navigating to Time Schedule", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Dashboard.this, MainActivity.class);
            startActivity(intent);
        });
    }


}


/*
package com.example.fitness_life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    private Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the buttons
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        // Set OnClickListener for Button 1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Example action: Show a Toast
                Toast.makeText(Dashboard.this, "Activity Selected", Toast.LENGTH_SHORT).show();
                // Optionally, navigate to a new activity
                Intent intent = new Intent(Dashboard.this, TopExercise.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for Button 2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Example action: Show a Toast
                Toast.makeText(Dashboard.this, "Heart rate selected", Toast.LENGTH_SHORT).show();
                // Optionally, navigate to a new activity
                Intent intent = new Intent(Dashboard.this, Heartrate.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for Button 3
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Example action: Show a Toast
                Toast.makeText(Dashboard.this, "Nutrition Selected", Toast.LENGTH_SHORT).show();
                // Optionally, navigate to a new activity
                Intent intent = new Intent(Dashboard.this, Nutrition.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for Button 4
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Example action: Show a Toast
                Toast.makeText(Dashboard.this, "Time Schedule Selected", Toast.LENGTH_SHORT).show();
                // Optionally, navigate to a new activity
                Intent intent = new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
*/