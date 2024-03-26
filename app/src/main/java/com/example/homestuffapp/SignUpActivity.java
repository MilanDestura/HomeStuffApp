package com.example.homestuffapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText signupFirstName, signupLastName, signupEmail, signupPhone, signupAddress, signupUsername, signupPassword, confirmPassword;
    private Button signupButton;
    private TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupFirstName = findViewById(R.id.tvFirstName);
        signupLastName = findViewById(R.id.tvLastName);
        signupEmail = findViewById(R.id.tvEmail);
        signupPhone = findViewById(R.id.tvPhone);
        signupAddress = findViewById(R.id.tvAddress);
        signupUsername = findViewById(R.id.tvUserName);
        signupPassword = findViewById(R.id.tvPassword);
        confirmPassword = findViewById(R.id.tvConfirmPsw);
        signupButton = findViewById(R.id.btSignUp);
        loginRedirectText = findViewById(R.id.tvLoginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = signupFirstName.getText().toString().trim();
                String lastName = signupLastName.getText().toString().trim();
                String phone = signupPhone.getText().toString().trim();
                String address = signupAddress.getText().toString().trim();
                String username = signupUsername.getText().toString().trim();
                String email = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                String rePsw = confirmPassword.getText().toString().trim();

                // Basic validation checks
                if (firstName.isEmpty()) {
                    signupFirstName.setError("First name cannot be empty");
                    return;
                }
                if (lastName.isEmpty()) {
                    signupLastName.setError("Last name cannot be empty");
                    return;
                }
                if (email.isEmpty()) {
                    signupEmail.setError("Email cannot be empty");
                    return;
                }
                if (phone.isEmpty()) {
                    signupPhone.setError("Phone cannot be empty");
                    return;
                }
                if (address.isEmpty()) {
                    signupAddress.setError("Address cannot be empty");
                    return;
                }
                if (username.isEmpty()) {
                    signupUsername.setError("Username cannot be empty");
                    return;
                }
                if (pass.isEmpty()) {
                    signupPassword.setError("Password cannot be empty");
                    return;
                }
                if (rePsw.isEmpty()) {
                    signupPassword.setError("Confirm password cannot be empty");
                    return;
                }

                long userId = new DBHelper(SignUpActivity.this).signupUser(firstName, lastName, email, phone, address, username, pass, rePsw);
                Intent intent = new Intent(SignUpActivity.this, MenuActivity.class);
                intent.putExtra("userId", userId);
                Log.e("userId", userId + "");

                // Simulate successful sign-up
                Toast.makeText(SignUpActivity.this, "Sign up successful" + userId, Toast.LENGTH_SHORT).show();
                startActivity(intent);
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
