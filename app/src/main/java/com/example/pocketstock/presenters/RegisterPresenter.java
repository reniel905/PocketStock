package com.example.pocketstock.presenters;

import android.content.Context;
import android.widget.Toast;

import com.example.pocketstock.contracts.RegisterContract;
import com.example.pocketstock.database.AppDatabase;
import com.example.pocketstock.factories.RegisterPresenterFactory;
import com.example.pocketstock.models.RegisterModel;
import com.example.pocketstock.models.User;

public class RegisterPresenter implements RegisterContract.RegisterPresenter {

    private final Context context;

    public RegisterPresenter(Context context){
        this.context = context;
    }

    @Override
    public boolean createUser(User user) {

        RegisterModel registerModel = new RegisterModel(context);

        if (!registerModel.checkUserNameExist(user.getUsername())) {

            registerModel.register(user);
            Toast.makeText(context, "Hi" + user.getFirstName() + ", your account has been successfully created!", Toast.LENGTH_SHORT).show();
            return true;

        } else {

            Toast.makeText(context, "Failed to create user. Username is already used.", Toast.LENGTH_SHORT).show();
            return false;

        }
    }
}
