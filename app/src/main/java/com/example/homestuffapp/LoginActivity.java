package com.example.homestuffapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private EditText loginUsername, loginPassword;
    private Button loginButton;
    private TextView signupRedirectText;
    private String uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);

        loginUsername = findViewById(R.id.tvUserNameLog);
        loginPassword = findViewById(R.id.tvPassword);
        loginButton = findViewById(R.id.btLogin);
        signupRedirectText = findViewById(R.id.tvSignupRedirectText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uName = loginUsername.getText().toString().trim();
                long isLoggedId = dbHelper.checkUser(uName, loginPassword.getText().toString().trim());
                if (isLoggedId > 0){
                     Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                     intent.putExtra("userId",isLoggedId);
                     intent.putExtra("userName",uName);
                     //Log.e("test", "user is logged " + isLoggedId);
                    Toast.makeText(LoginActivity.this, "Login Succesful", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }
}
