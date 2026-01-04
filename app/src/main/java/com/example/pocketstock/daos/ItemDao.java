package com.example.pocketstock.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Upsert;

import com.example.pocketstock.models.Item;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM items")
    List<Item> getItems();

    @Upsert
    void insertItem(Item item);

    @Query("DELETE FROM items WHERE id = :id")
    void deleteItem(int id);


    @Query("UPDATE items SET product_name = :productName, category = :category, price = :price, quantity = :quantity WHERE id = :id")
    void updateItem(int id, String productName, String category, double price, int quantity);

}
