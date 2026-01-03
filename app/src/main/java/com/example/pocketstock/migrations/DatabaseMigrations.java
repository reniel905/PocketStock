package com.example.pocketstock.migrations;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseMigrations {

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `users_new` (" +
                            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`username` TEXT, " +
                            "`password` TEXT, " +
                            "`email` TEXT, " +
                            "`first_name` TEXT, " +
                            "`last_name` TEXT)"

            );

            database.execSQL(
                    "INSERT INTO `users_new` (id, username, password, email, first_name, last_name) " +
                            "SELECT id, username, password, email, first_name, last_name FROM `users`"
            );

            database.execSQL("DROP TABLE `users`");

            database.execSQL("ALTER TABLE `users_new` RENAME TO `users`");
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `items` (" +
                            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`product_name` TEXT, " +
                            "`category` TEXT, " +
                            "`price` REAL, " +
                            "`quantity` INTEGER)"

            );

            database.execSQL("CREATE TABLE items_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, category TEXT, price REAL NOT NULL, product_name TEXT, quantity INTEGER NOT NULL)");

            database.execSQL("INSERT INTO items_new (id, category, price, product_name, quantity) SELECT id, category, COALESCE(price, 0), product_name, COALESCE(quantity, 0) FROM items");

            database.execSQL("DROP TABLE `items`");

            database.execSQL("ALTER TABLE `items_new` RENAME TO `items`");


        }
    };

}
