package com.example.pocketstock.contracts;

import com.example.pocketstock.models.UserModel;
import java.util.List;

public interface LoginContract {

    interface LoginView {

        void setLoading(boolean isLoading);
        void wrongCredential();
        void loginSuccess();
        void navigateToDashboard();
        void checkLogin();

    }

    interface LoginModel {

        UserModel getUser(String username);

    }

    interface LoginPresenter {

        boolean validateCredentials(String username, String password);

    }

}
