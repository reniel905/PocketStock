package com.example.pocketstock.contracts;

import com.example.pocketstock.models.User;

public interface LoginContract {

    interface LoginView {

        void setLoading(boolean isLoading);
        void wrongCredential();
        void loginSuccess();
        void navigateToDashboard();
        void navigateToRegistration();
        void checkLogin();

    }

    interface LoginModel {

        User getUser(String username);

    }

    interface LoginPresenter {

        boolean validateCredentials(String username, String password);

    }

}
