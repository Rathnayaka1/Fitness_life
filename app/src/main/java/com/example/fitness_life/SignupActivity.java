package com.example.fitness_life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etPhone, etEmail;
    private Button btnDone;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize UI elements
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        btnDone = findViewById(R.id.btn_done);


        // Set up the button click listener
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate the input fields
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String phone = etPhone.getText().toString();
                String email = etEmail.getText().toString();

                if (username.isEmpty() || password.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Show success message
                    Toast.makeText(SignupActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();

                    // Navigate to the DashboardActivity
                    Intent intent = new Intent(SignupActivity.this, Dashboard.class);
                    startActivity(intent);

                    // Optionally, finish MainActivity so that user can't navigate back
                    finish();
                }
            }
        });
    }
}
