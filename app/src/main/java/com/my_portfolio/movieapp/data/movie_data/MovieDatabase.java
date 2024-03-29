package com.my_portfolio.movieapp.data.movie_data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.my_portfolio.movieapp.utils.pojo.MovieResponseResult;

@Database(entities = {MovieResponseResult.class}, version = 1,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase database;
    private static String DB_NAME = "movies.db";
    private static final Object LOCK = new Object();

    public static MovieDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context,MovieDatabase.class,DB_NAME)
                        .fallbackToDestructiveMigration().build();
            }
            return database;
        }
    }

    public abstract MovieDao movieDao();
}
