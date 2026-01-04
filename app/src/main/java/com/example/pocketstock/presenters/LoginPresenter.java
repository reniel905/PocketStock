package com.example.pocketstock.presenters;

import android.content.Context;

import com.example.pocketstock.contracts.LoginContract;
import com.example.pocketstock.models.LoginModel;
import com.example.pocketstock.models.User;

public class LoginPresenter implements LoginContract.LoginPresenter{

    private final Context context;

    public LoginPresenter(Context context) {
        this.context = context;
    }

    @Override
    public boolean validateCredentials(String username, String password) {

        LoginModel loginModel = new LoginModel(context);
        User user = loginModel.getUser(username);

        if (user.getUsername().isEmpty() && user.getPassword().isEmpty()) {
            return false;
        }

        return user.getUsername().equals(username) && user.getPassword().equals(password);

    }
}
