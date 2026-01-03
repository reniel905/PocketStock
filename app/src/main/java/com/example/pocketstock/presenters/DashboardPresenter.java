package com.example.pocketstock.presenters;

import android.content.Context;

import com.example.pocketstock.contracts.DashboardContract;
import com.example.pocketstock.database.AppDatabase;
import com.example.pocketstock.models.DashboardModel;
import com.example.pocketstock.models.Item;

import java.util.Arrays;
import java.util.List;

public class DashboardPresenter implements DashboardContract.DashboardPresenter {

    DashboardModel dashboardModel;

    public DashboardPresenter(Context context) {
        dashboardModel = new DashboardModel(context);
    }

    @Override
    public List<Item> sortItems() {


        return dashboardModel.getItems();

    }

    @Override
    public void addNewItem(Item item) {
        dashboardModel.addItem(item);
    }
}
