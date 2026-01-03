package com.example.pocketstock.contracts;

import com.example.pocketstock.models.User;

public interface RegisterContract {

    interface RegisterView {

        void navigateToLogin();
        boolean checkPasswordMatch(String password, String confirmPassword);
        void onRegisterClick();

    }

    interface RegisterModel {

        boolean checkUserNameExist(String username);
        void register(User user);

    }

    interface RegisterPresenter {

        boolean createUser(User user);

    }

}
