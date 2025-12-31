package com.example.pocketstock.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

import com.example.pocketstock.daos.LoginDao;
import com.example.pocketstock.models.Login;

@Database(entities = {Login.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LoginDao loginDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {

        if (INSTANCE == null){

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "DB_POCKETSTOCK")
                    .allowMainThreadQueries() // Not recommended for production, but okay for simple learning
                    .build();
        }

        return INSTANCE;
    }

}
