package com.example.fitness_life;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etPhone, etEmail;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up); // Fixed the resource name

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        btnDone = findViewById(R.id.btn_done);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInputs();
            }
        });
    }

    private void validateInputs() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
            // Proceed with further logic like storing data or moving to another screen
        }
    }
}
