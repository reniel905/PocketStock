package com.example.pocketstock.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pocketstock.models.Login;

@Dao
public interface LoginDao {

    @Insert
    void insertLogin(Login login);

    @Query("SELECT username FROM logins")
    String[] getLogin();

    @Delete
    void deleteLogin(Login login);

}
