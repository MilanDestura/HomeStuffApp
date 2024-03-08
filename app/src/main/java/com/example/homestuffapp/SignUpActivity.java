package com.example.homestuffapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText signupEmail, signupPassword;
    private Button signupButton;
    private TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupEmail = findViewById(R.id.tvEmail);
        signupPassword = findViewById(R.id.tvPassword);
        signupButton = findViewById(R.id.btSignUp);
        loginRedirectText = findViewById(R.id.tvLoginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();

                // Basic validation checks
                if (user.isEmpty()) {
                    signupEmail.setError("Email cannot be empty");
                    return; // Return early if validation fails
                }
                if (pass.isEmpty()) {
                    signupPassword.setError("Password cannot be empty");
                    return; // Return early if validation fails
                }

                // Simulate successful sign-up
                Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUpActivity.this, MenuActivity.class));

            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }
}
