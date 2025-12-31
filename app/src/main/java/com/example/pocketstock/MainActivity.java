package com.example.pocketstock;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pocketstock.activities.DashboardActivity;
import com.example.pocketstock.contracts.DashboardContract;
import com.example.pocketstock.contracts.LoginContract;
import com.example.pocketstock.database.AppDatabase;
import com.example.pocketstock.factories.LoginPresenterFactory;
import com.example.pocketstock.models.Login;
import com.example.pocketstock.presenters.LoginPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements LoginContract.LoginView {

    private LoginContract.LoginPresenter loginPresenter;
    private ProgressBar progressBar;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = AppDatabase.getDbInstance(this);

        checkLogin();

        TextInputEditText userNameInput, passwordInput;
        Button button;

        loginPresenter = LoginPresenterFactory.createLoginPresenter();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);
        button = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userNameInput.getText().toString().isEmpty() || passwordInput.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivity.this, "Username or password is empty.", Toast.LENGTH_SHORT).show();
                    return;

                }

                setLoading(true);
                button.setEnabled(false);
                button.setText("Logging in, please wait...");

                new CountDownTimer(2000, 100) {

                    @Override
                    public void onTick(long l) {


                    }

                    @Override
                    public void onFinish() {
                        try {
                            if (loginPresenter.validateCredentials(userNameInput.getText().toString(), passwordInput.getText().toString())){
                                loginSuccess();
                                setLoading(false);
                                button.setEnabled(true);
                                button.setText("Login");
                                saveLogin(userNameInput.getText().toString());
                                navigateToDashboard();
                            } else {
                                wrongCredential();
                                setLoading(false);
                                button.setEnabled(true);
                                button.setText("Login");
                            }
                        } catch (Exception e) {

                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }.start();
            }
        });
    }

    private void saveLogin(String username) {

        db.loginDao().deleteLogin(new Login(username));
        db.loginDao().insertLogin(new Login(username));
    }

    @Override
    public void setLoading(boolean isLoading) {

        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }


    }

    @Override
    public void wrongCredential() {

        Toast.makeText(this, "Login failed. Wrong Credentials", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {

        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void navigateToDashboard() {

        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void checkLogin() {
        if (db.loginDao().getLogin().length > 0) {
            navigateToDashboard();
        }
    }
}