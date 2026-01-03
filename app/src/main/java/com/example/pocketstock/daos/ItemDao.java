package com.example.pocketstock.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pocketstock.models.Item;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM items")
    List<Item> getItems();

    @Insert
    void insertItem(Item item);

    @Query("DELETE FROM items WHERE id = :id")
    void deleteItem(int id);

    @Query("UPDATE items SET quantity = quantity - 1 WHERE id = :id")
    void updateItem(int id);

}
