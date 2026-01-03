package com.example.pocketstock.models;

import android.content.Context;

import com.example.pocketstock.contracts.LoginContract;
import com.example.pocketstock.database.AppDatabase;

import java.util.List;

public class LoginModel implements LoginContract.LoginModel{

    private Context context;

    public LoginModel(Context context){
        this.context = context;
    }

    @Override
    public User getUser(String username) {

        AppDatabase db = AppDatabase.getDbInstance(context);
        List<User> users = db.userDao().getUsers();

        if (!users.isEmpty()) {

            for (User user: users
                 ) {

                if (username.equals(user.getUsername())){

                    return user;
                }

            }

        }

        return new User();
    }
}
