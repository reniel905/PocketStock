package com.example.pocketstock.presenters;

import android.content.Context;

import com.example.pocketstock.contracts.DashboardContract;
import com.example.pocketstock.models.DashboardModel;
import com.example.pocketstock.models.Item;

import java.util.List;

public class DashboardPresenter implements DashboardContract.DashboardPresenter {

    private final DashboardContract.DashboardModel dashboardModel;
    private final DashboardContract.DashboardView dashboardView;

    public DashboardPresenter(Context context, DashboardContract.DashboardView dashboardView) {
        dashboardModel = new DashboardModel(context);
        this.dashboardView = dashboardView;
    }

    @Override
    public List<Item> sortItems() {
        return dashboardModel.getItems();
    }

    @Override
    public void addNewItem(Item item) {
        dashboardModel.addItem(item);
    }

    @Override
    public void updateItem(int id, String productName, String category, double price, int quantity) {
        dashboardModel.updateItem(id, productName, category, price, quantity);
        dashboardView.showItems();
    }

    @Override
    public void deleteItem(int id) {
        dashboardModel.deleteItem(id);
        dashboardView.showItems();
    }
}
