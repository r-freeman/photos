package com.example.photos.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;

/**
 * AppDatabase is a Singleton class which means only one instance of this class should
 * be available to the application. The reason we are using the Singleton pattern here is because we
 * don't want to cause problems by creating two or more databases at the same time.
 */
@Database(entities = PhotoEntity.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    // define the database name stored in data/data/com.example.photos/databases/
    public static final String DATABASE_NAME = "AppDatabase.db";
    // volatile because we always want an up to date instance of AppDatabase
    private static volatile AppDatabase instance;
    // create a lock object to prevent issues with concurrent database operations
    private static final Object LOCK = new Object();

    // An instance of AppDatabase will have access to all of the methods
    // in the Photo Data Access Object interface
    public abstract PhotoDao photoDao();

    public static AppDatabase getInstance(Context context) {
        // check if there is already an instance
        if (instance == null) {
            // get a lock on the database to prevent issues with concurrent operations
            synchronized (LOCK) {
                // double check instance to be sure
                if (instance == null) {
                    // create an instance of the database with Room
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
