package com.example.fitness_life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TopExercise extends AppCompatActivity {

    Button btnYoga, btnRunning, btnCycling, btnGym, btnMoreExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topexercise);

        // Initializing buttons
        btnYoga = findViewById(R.id.btn_yoga);
        btnRunning = findViewById(R.id.btn_running);
        btnCycling = findViewById(R.id.btn_cycling);
        btnGym = findViewById(R.id.btn_gym);
        btnMoreExercises = findViewById(R.id.btn_more_exercises);

        // Set click listeners for each button

        btnCycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopExercise.this, Age.class);
                startActivity(intent);
            }
        });

        btnGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopExercise.this, Age.class);
                startActivity(intent);
            }
        });

        btnMoreExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopExercise.this, Calorie.class);
                startActivity(intent);
            }
        });
    }
}
