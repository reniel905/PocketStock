package com.example.pocketstock.models;

import android.content.Context;
import android.util.Log;

import com.example.pocketstock.contracts.RegisterContract;
import com.example.pocketstock.database.AppDatabase;

import java.util.List;

public class RegisterModel implements RegisterContract.RegisterModel {

    private final Context context;
    private final AppDatabase db;

    public RegisterModel(Context context) {
        this.context = context;
        db = AppDatabase.getDbInstance(context);
    }


    @Override
    public boolean checkUserNameExist(String username) {

        List<User> users = db.userDao().getUsers();

        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void register(User user) {
        db.userDao().insertUser(user);
    }
}
