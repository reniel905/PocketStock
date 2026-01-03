package com.example.pocketstock.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketstock.MainActivity;
import com.example.pocketstock.R;
import com.example.pocketstock.adapters.ItemListAdapter;
import com.example.pocketstock.contracts.DashboardContract;
import com.example.pocketstock.database.AppDatabase;
import com.example.pocketstock.factories.DashboardPresenterFactory;
import com.example.pocketstock.models.Item;
import com.example.pocketstock.models.Login;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DashboardActivity extends AppCompatActivity implements DashboardContract.DashboardView {


    private final AppDatabase db = AppDatabase.getDbInstance(this);
    private String username;
    private DashboardContract.DashboardPresenter dashboardPresenter;
    private int itemCount;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dashboardPresenter = DashboardPresenterFactory.createDashboardPresenter(this);

        TextView welcomeText = findViewById(R.id.welcomeText);
        Button logoutButton = findViewById(R.id.logoutButton);
        Button addItemButton = findViewById(R.id.addItemButton);
        username = db.loginDao().getLogin()[0];

        showItems();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure you want to logout?")
                        .setTitle("Logout")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logoutUser();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View dialogView = getLayoutInflater().inflate(R.layout.add_item_layout, null);

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(v.getContext());
                builder.setView(dialogView).setPositiveButton("Add Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        onItemAdd(dialogView);

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                    }
                }).setTitle("Add Item").setMessage("Add an item to your inventory.");

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        welcomeText.setText("Hi there, " + username);
    }

    private void logoutUser() {
       db.loginDao().deleteLogin(new Login(username));
       navigateToLogin();
    }

    @Override
    public void showLoggedInUser(String username) {

    }

    @Override
    public void showItems() {

        List<Item> items = dashboardPresenter.sortItems();
        RecyclerView itemListView = findViewById(R.id.productListRecView);
        TextView noProductsText = findViewById(R.id.noProductsText);
        ItemListAdapter itemListAdapter = new ItemListAdapter(this, items);
        itemListView.setLayoutManager(new LinearLayoutManager(this));
        itemListView.setAdapter(itemListAdapter);

        itemCount = itemListAdapter.getItemCount();
        totalPrice = 0; // set to 0 when refreshed to get accurate data.

        for (Item item: items
             ) {

            totalPrice += (double) item.price * item.quantity;

        }

        itemCount();
        totalValue();

        if (itemListAdapter.getItemCount() < 0) {

            itemListView.setVisibility(View.GONE);
            noProductsText.setVisibility(View.VISIBLE);

        } else {

            noProductsText.setVisibility(View.GONE);
            itemListView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void totalValue() {

        double totalValue = totalPrice * itemCount;

        // double totalValue = 23879; // for testing

        TextView totalItemsValue = findViewById(R.id.totalValue);
        totalItemsValue.setText(String.valueOf(totalValue));

        if (totalValue < 10000) {
            totalItemsValue.setText(String.valueOf(totalValue).substring(0,1).concat(".").concat(String.valueOf(totalValue).substring(1,2)).concat("K"));
        } else if (totalValue >= 10000) {
            totalItemsValue.setText(String.valueOf(totalValue).substring(0,2).concat(".").concat(String.valueOf(totalValue).substring(2,3)).concat("K"));
        } else if (totalValue >= 100000) {
            totalItemsValue.setText(String.valueOf(totalValue).substring(0,3).concat(".").concat(String.valueOf(totalValue).substring(3,4)).concat("K"));
        }

    }

    @Override
    public void itemCount() {

        TextView totalItemCount = findViewById(R.id.totalItemCount);
        totalItemCount.setText(String.valueOf(itemCount));

    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemAdd(View v) {

        TextInputEditText itemName, category, price, quantity;
        itemName = v.findViewById(R.id.itemNameInput);
        category = v.findViewById(R.id.categoryInput);
        price = v.findViewById(R.id.priceInput);
        quantity = v.findViewById(R.id.quantityInput);

        dashboardPresenter.addNewItem(new Item(itemName.getText().toString(), category.getText().toString(), Double.parseDouble(price.getText().toString()), Integer.parseInt(quantity.getText().toString())));
        showItems();
    }

}