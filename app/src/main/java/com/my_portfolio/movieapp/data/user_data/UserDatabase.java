package com.my_portfolio.movieapp.data.user_data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase userDatabase;
    private static final String DB_NAME = "user.db";
    private static Object LOCK = new Object();

    public static synchronized UserDatabase getUserDatabase(Context context) {
        synchronized (LOCK) {
            if (userDatabase == null) {
                userDatabase = Room.databaseBuilder(context, UserDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration().build();
            }
            return userDatabase;
        }
    }

    public abstract UserDao userDao();
}
