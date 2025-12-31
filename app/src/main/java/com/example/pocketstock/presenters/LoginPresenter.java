package com.example.pocketstock.presenters;

import com.example.pocketstock.contracts.LoginContract;
import com.example.pocketstock.models.LoginModel;
import com.example.pocketstock.models.UserModel;

public class LoginPresenter implements LoginContract.LoginPresenter{
    @Override
    public boolean validateCredentials(String username, String password) {

        LoginModel loginModel = new LoginModel();
        UserModel user = loginModel.getUser(username);

        if (user.getUsername().isEmpty() && user.getPassword().isEmpty()) {
            return false;
        }

        return user.getUsername().equals(username) && user.getPassword().equals(password);

    }
}
