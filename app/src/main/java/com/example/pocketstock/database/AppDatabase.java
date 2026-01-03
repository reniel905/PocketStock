package com.example.pocketstock.database;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

import com.example.pocketstock.daos.ItemDao;
import com.example.pocketstock.daos.LoginDao;
import com.example.pocketstock.daos.UserDao;
import com.example.pocketstock.migrations.DatabaseMigrations;
import com.example.pocketstock.models.Item;
import com.example.pocketstock.models.Login;
import com.example.pocketstock.models.User;

@Database(entities = {Login.class, User.class, Item.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LoginDao loginDao();
    public abstract UserDao userDao();
    public abstract ItemDao itemDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {

        if (INSTANCE == null){

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "DB_POCKETSTOCK")
                    .addMigrations(DatabaseMigrations.MIGRATION_1_2)
                    .addMigrations(DatabaseMigrations.MIGRATION_2_3)
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }

}
