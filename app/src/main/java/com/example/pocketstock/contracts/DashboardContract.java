package com.example.pocketstock.contracts;

import android.view.View;

import com.example.pocketstock.models.Item;

import java.util.List;

public interface DashboardContract {

    interface DashboardView {

        void showLoggedInUser(String username);

        //Function to show all products.
        void showItems();

        //Function to total all value of the products.
        void totalValue();

        //Function to count all unique products.
        void itemCount();

        void navigateToLogin();
        void onItemAdd(View v);

    }

    interface DashboardModel {

        List<Item> getItems();
        void getUser();
        void addItem(Item item);
        void updateItem(int id, String productName, String category, double price, int quantity);
        void deleteItem(int id);

    }

    interface DashboardPresenter {

         List<Item> sortItems();
         void addNewItem(Item item);
         void updateItem(int id, String productName, String category, double price, int quantity);
         void deleteItem(int id);

    }

}
