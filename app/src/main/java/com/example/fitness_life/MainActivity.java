package com.example.fitness_life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnYoga, btnRunning, btnCycling, btnGym, btnCaloryCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_topexercise);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.topexercise), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize buttons
        btnYoga = findViewById(R.id.btnYoga);
        btnRunning = findViewById(R.id.btnrunning);
        btnCycling = findViewById(R.id.btnCycling);
        btnGym = findViewById(R.id.btnGym);
        btnCaloryCalculator = findViewById(R.id.btnCalorycalculator);

        // Set click listeners
        btnGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to GymActivity
                Intent intent = new Intent(MainActivity.this, GymActivity.class);
                startActivity(intent);
            }
        });

        // You can add other button click listeners here as needed
    }
}