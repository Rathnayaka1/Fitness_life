package com.example.fitness_life;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Heartrate extends AppCompatActivity {

    private TextView measuringText;
    private ImageView fingerprintIcon;
    private int progress = 25; // Starts at 25%

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartrate);

        // Initialize UI components
        measuringText = findViewById(R.id.measuring_text);
        fingerprintIcon = findViewById(R.id.fingerprint_icon);

        // Simulate heart rate measurement progress
        startHeartRateMeasurement();
    }

    private void startHeartRateMeasurement() {
        new Thread(() -> {
            while (progress < 100) {
                try {
                    Thread.sleep(1000); // Simulate delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress += 25;
                runOnUiThread(() -> measuringText.setText("Measuring... (" + progress + "%)"));
            }
            runOnUiThread(() -> measuringText.setText("Measurement Complete!"));
        }).start();
    }
}
