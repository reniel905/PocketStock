package com.example.pocketstock.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "logins")
public class Login {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "username")
    public String username;

    public Login (String username){
        this.username = username;
    }
}
