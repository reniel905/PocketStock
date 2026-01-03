package com.example.pocketstock.models;

import android.content.Context;

import com.example.pocketstock.contracts.DashboardContract;
import com.example.pocketstock.database.AppDatabase;

import java.util.List;

public class DashboardModel implements DashboardContract.DashboardModel {

    Context context;
    AppDatabase db;

    public DashboardModel(Context context) {

        this.context = context;
        db = AppDatabase.getDbInstance(context);

    }

    @Override
    public List<Item> getItems() {

        return db.itemDao().getItems();

    }

    @Override
    public void getUser() {

    }

    @Override
    public void addItem(Item item) {

        db.itemDao().insertItem(item);

    }
}
