package com.example.pocketstock.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pocketstock.MainActivity;
import com.example.pocketstock.R;
import com.example.pocketstock.contracts.RegisterContract;
import com.example.pocketstock.factories.RegisterPresenterFactory;
import com.example.pocketstock.models.User;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.RegisterView {

    private TextInputEditText firstNameInput, lastNameInput, emailInput, userNameInput, passwordInput, confirmPasswordInput;
    private Button registerButton;
    private RegisterContract.RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        registerPresenter = RegisterPresenterFactory.createRegisterPresenter(this);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        emailInput = findViewById(R.id.emailInput);
        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        registerButton = findViewById(R.id.registerButton);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onRegisterClick();

            }
        });

    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
     }

    @Override
    public boolean checkPasswordMatch(String password, String confirmPassword) {

        return password.equals(confirmPassword);

    }

    @Override
    public void onRegisterClick() {

        if (passwordInput.getText().toString().isEmpty() && confirmPasswordInput.getText().toString().isEmpty()) {
            Toast.makeText(this, "Passwords are required." , Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            if (!checkPasswordMatch(passwordInput.getText().toString(), confirmPasswordInput.getText().toString())) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            } else {
               if ( registerPresenter.createUser(new User(userNameInput.getText().toString(), passwordInput.getText().toString(), firstNameInput.getText().toString(), lastNameInput.getText().toString(), emailInput.getText().toString()))) {
                   navigateToLogin();
               }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString() , Toast.LENGTH_SHORT).show();
        }
    }
}