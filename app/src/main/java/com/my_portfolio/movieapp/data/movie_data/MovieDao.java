package com.my_portfolio.movieapp.data.movie_data;

import android.graphics.Movie;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.my_portfolio.movieapp.utils.pojo.MovieResponseResult;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    LiveData<List<MovieResponseResult>> getAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MovieResponseResult> movieResponseResult);

    @Query("SELECT * FROM movies WHERE id == :movieId")
    MovieResponseResult getMovieByID(int movieId);

    @Query("DELETE FROM movies")
    void deleteMovies();
}
