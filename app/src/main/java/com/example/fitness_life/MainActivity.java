package com.example.fitness_life;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private CardView cardRunning, cardGym, cardCycling, cardYoga;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize CardViews
        cardRunning = findViewById(R.id.cardRunning);
        cardGym = findViewById(R.id.cardGym);
        cardCycling = findViewById(R.id.cardCycling);
        cardYoga = findViewById(R.id.cardYoga);

        // Set Click Listeners
        cardRunning.setOnClickListener(view -> showToast("Running Scheduled!"));
        cardGym.setOnClickListener(view -> showToast("Gym Scheduled!"));
        cardCycling.setOnClickListener(view -> showToast("Cycling Scheduled!"));
        cardYoga.setOnClickListener(view -> showToast("Yoga Scheduled!"));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
