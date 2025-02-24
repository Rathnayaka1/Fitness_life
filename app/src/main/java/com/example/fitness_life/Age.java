package com.example.fitness_life;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Age extends AppCompatActivity {

    private Button btnUnder25, btn25_35, btn35_45, btn45Plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        // Initialize the buttons
        btnUnder25 = findViewById(R.id.btnUnder25);
        btn25_35 = findViewById(R.id.btn25_35);
        btn35_45 = findViewById(R.id.btn35_45);
        btn45Plus = findViewById(R.id.btn45Plus);

        // Set onClick listeners for each button

        btnUnder25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message
                Toast.makeText(Age.this, "Age Group: Under 25", Toast.LENGTH_SHORT).show();
                // You can perform additional actions here, like navigating to another activity
            }
        });

        btn25_35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message
                Toast.makeText(Age.this, "Age Group: 25 - 35", Toast.LENGTH_SHORT).show();
                // You can perform additional actions here, like navigating to another activity
            }
        });

        btn35_45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message
                Toast.makeText(Age.this, "Age Group: 35 - 45", Toast.LENGTH_SHORT).show();
                // You can perform additional actions here, like navigating to another activity
            }
        });

        btn45Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message
                Toast.makeText(Age.this, "Age Group: 45+", Toast.LENGTH_SHORT).show();
                // You can perform additional actions here, like navigating to another activity
            }
        });
    }
}
