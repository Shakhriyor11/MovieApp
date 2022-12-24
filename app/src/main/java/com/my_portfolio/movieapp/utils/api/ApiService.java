package com.my_portfolio.movieapp.utils.api;

import com.my_portfolio.movieapp.utils.pojo.MovieResponse;
import com.my_portfolio.movieapp.utils.pojo.MovieResponseResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("3/discover/movie")
    Call<List<MovieResponseResult>> getMovies(@Query("api_key") String API_KEY, @Query("language") String LANGUAGE, @Query("sort_by") String SORT_BY);

    @GET("3/search/movie")
    Call<MovieResponse> getMoviesByQuery(@Query("api_key") String api_key, @Query("query") String query);

}
