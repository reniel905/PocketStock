package com.example.pocketstock.factories;

import android.content.Context;

import com.example.pocketstock.contracts.RegisterContract;
import com.example.pocketstock.presenters.RegisterPresenter;

public class RegisterPresenterFactory {

    public static RegisterContract.RegisterPresenter createRegisterPresenter(Context context) {

        return new RegisterPresenter(context);

    }

}
