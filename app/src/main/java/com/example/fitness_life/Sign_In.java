package com.example.fitness_life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Sign_In extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnSignIn;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize Views
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        tvSignUp = findViewById(R.id.tv_sign_up);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Default credentials
                String defaultUsername = "admin";
                String defaultPassword = "1234";

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Sign_In.this, "Please enter Username and Password", Toast.LENGTH_SHORT).show();
                } else if (username.equals(defaultUsername) && password.equals(defaultPassword)) {
                    // Navigate to Dashboard
                    Intent intent = new Intent(Sign_In.this, Dashboard.class);
                    intent.putExtra("USERNAME", username);
                    startActivity(intent);
                    finish(); // Close Sign In screen
                } else {
                    Toast.makeText(Sign_In.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle Sign Up click (Uncomment if SignUp is required)
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_In.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}


/*package com.example.fitness_life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Sign_In extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnSignIn;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in); // Ensure the correct layout file is used

        // Initialize Views
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        tvSignUp = findViewById(R.id.tv_sign_up);

        // Handle "Done" Button Click
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Default credentials
                String defaultUsername = "admin";
                String defaultPassword = "1234";

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Sign_In.this, "Please enter Username and Password", Toast.LENGTH_SHORT).show();
                } else if (username.equals(defaultUsername) && password.equals(defaultPassword)) {
                    // Navigate to Dashboard instead of Welcome Screen
                    Intent intent = new Intent(Sign_In.this, Dashboard.class);
                    intent.putExtra("USERNAME", username); // Optional: Pass username to Dashboard
                    startActivity(intent);
                    finish(); // Close Sign In screen
                } else {
                    Toast.makeText(Sign_In.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });



        // Handle "Sign Up" Text Click
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_In.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
*/