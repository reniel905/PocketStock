package com.example.pocketstock.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pocketstock.MainActivity;
import com.example.pocketstock.R;
import com.example.pocketstock.contracts.DashboardContract;
import com.example.pocketstock.database.AppDatabase;
import com.example.pocketstock.models.Login;

public class DashboardActivity extends AppCompatActivity implements DashboardContract.DashboardView {


    private final AppDatabase db = AppDatabase.getDbInstance(this);
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView welcomeText;
        Button logoutButton;
        username = db.loginDao().getLogin()[0];

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        welcomeText = findViewById(R.id.welcomeText);
        logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logoutUser();

            }
        });

        welcomeText.setText("Hi there, " + username);
    }

    private void logoutUser() {
       db.loginDao().deleteLogin(new Login(username));
       navigateToLogin();
    }

    @Override
    public void showLoggedInUser(String username) {

    }

    @Override
    public void showItems() {

    }

    @Override
    public void totalValue() {

    }

    @Override
    public void itemCount() {

    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}