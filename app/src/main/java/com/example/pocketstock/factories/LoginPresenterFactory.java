package com.example.pocketstock.factories;

import com.example.pocketstock.contracts.LoginContract;
import com.example.pocketstock.presenters.LoginPresenter;

public class LoginPresenterFactory {

    public static LoginContract.LoginPresenter createLoginPresenter() {

        // initiate or get the database here, not in the activity.

        return new LoginPresenter();

    }

}
