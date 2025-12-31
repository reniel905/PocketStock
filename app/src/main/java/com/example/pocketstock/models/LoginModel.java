package com.example.pocketstock.models;

import com.example.pocketstock.contracts.LoginContract;

import java.util.List;

public class LoginModel implements LoginContract.LoginModel{


    private static final List<UserModel> users = List.of(new UserModel("admin", "admin123"));

    @Override
    public UserModel getUser(String username) {

        if (!users.isEmpty()) {

            for (UserModel user: users
                 ) {

                if (username.equalsIgnoreCase(user.getUsername())){

                    return user;
                }

            }

        }

        return new UserModel("", "");
    }
}
