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
    Observable<MovieResponse> getMovies(@Query("api_key") String API_KEY, @Query("language") String LANGUAGE, @Query("sort_by") String SORT_BY);

    @GET("3/search/movie")
    Observable<MovieResponse> getMoviesByQuery(@Query("api_key") String API_KEY, @Query("language") String LANGUAGE, @Query("query") String QUERY);

    @GET("3/search/person")
    Observable<MovieResponse> getPersonByQuery(@Query("api_key") String API_KEY, @Query("language") String LANGUAGE, @Query("query") String QUERY);

}
