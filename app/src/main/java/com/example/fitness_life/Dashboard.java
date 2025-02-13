package com.example.fitness_life;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Get TextView
        TextView forYouText = findViewById(R.id.for_you_text);

        // Highlight "For You"
        SpannableString highlightText = new SpannableString("For You");
        highlightText.setSpan(new BackgroundColorSpan(Color.YELLOW), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set Highlighted Text
        forYouText.setText(highlightText);
    }
}
