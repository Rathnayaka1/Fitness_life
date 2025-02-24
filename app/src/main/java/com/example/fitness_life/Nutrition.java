package com.example.fitness_life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Nutrition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        Button buttonVeg = findViewById(R.id.button);
        Button buttonChicken = findViewById(R.id.button6);
        Button buttonBanana = findViewById(R.id.button7);
        Button buttonMilk = findViewById(R.id.button8);


    }
}