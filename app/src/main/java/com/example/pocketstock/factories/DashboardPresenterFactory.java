package com.example.pocketstock.factories;

import android.content.Context;

import com.example.pocketstock.contracts.DashboardContract;
import com.example.pocketstock.presenters.DashboardPresenter;

public class DashboardPresenterFactory {

    public static DashboardContract.DashboardPresenter createDashboardPresenter(Context context) {

        return new DashboardPresenter(context);

    }
}
