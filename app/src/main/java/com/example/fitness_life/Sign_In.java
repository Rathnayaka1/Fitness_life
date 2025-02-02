package com.example.fitness_life;  // Replace with your actual package name

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Sign_In extends AppCompatActivity {

    // Declare variables for the UI elements
    private EditText etUsername, etPassword;
    private Button btnSignIn;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);  // Use your layout file name here

        // Initialize the UI elements
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        tvSignUp = findViewById(R.id.tv_sign_up);

        // Set a click listener for the Sign In button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the values from the EditTexts
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Perform Sign In logic (You can replace this with actual authentication)
                if (username.isEmpty() || password.isEmpty()) {
                    // Show a toast if the fields are empty
                    Toast.makeText(Sign_In.this, "Please fill out both fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Show a toast with the entered values (This can be replaced with real login logic)
                    Toast.makeText(Sign_In.this, "Sign In successful", Toast.LENGTH_SHORT).show();
                    // You can perform actual login here or navigate to the next screen
                }
            }
        });

        // Set a click listener for the Sign Up link
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle sign up logic or navigate to sign-up activity
                // Example: Start a new activity for sign-up
                // Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                // startActivity(intent);

                // For demonstration, show a toast
                Toast.makeText(Sign_In.this, "Redirecting to Sign Up", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
