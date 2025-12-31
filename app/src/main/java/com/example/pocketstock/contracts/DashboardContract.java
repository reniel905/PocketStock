package com.example.pocketstock.contracts;

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

    }

    interface DashboardModel {

        interface OnFinishedListener {
            void onFinished(String string);
        }

        void getItems();
        void getUser();

    }

    interface DashboardPresenter {

        void onShowItems();
        void onDestroy();

    }

}
