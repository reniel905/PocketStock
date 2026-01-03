package com.example.pocketstock.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "product_name")
    public String productName;

    @ColumnInfo
    public String category;

    @ColumnInfo
    public double price;

    @ColumnInfo
    public int quantity;

    public Item(String productName, String category, double price, int quantity) {
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.productName = productName;
    }
}
