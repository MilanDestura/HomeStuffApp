package com.example.homestuffapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPhone,
            etAddress, etUsername, etPassword, etConfirmPassword;

    // Define a variable to store the user ID
    private long userId;
    private Button btnUpdateProfile;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        // Initialize views
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etUsername = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPsw);

        btnUpdateProfile = findViewById(R.id.btUpdate);

        // Get the user ID from intent extras or any other source
        userId = getIntent().getLongExtra("id", -1);
        Log.e("teste", userId + "");

        // Set click listener for the update profile button
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

//    private void loadProfile(){
//
//    }

    private void updateProfile() {
        // Retrieve updated profile information from EditText fields
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String userName = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // Validate if any fields are empty
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                address.isEmpty() || userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(ProfileActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            Toast.makeText(ProfileActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update profile using DBHelper
        boolean isUpdated = new DBHelper(ProfileActivity.this).updateUserProfile(firstName, lastName, email, phone, address, userName, password, userId);

        if (isUpdated) {
            Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
            // Clear password fields
            etPassword.setText("");
            etConfirmPassword.setText("");
        } else {
            Toast.makeText(ProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }
}
