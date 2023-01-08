package com.my_portfolio.movieapp.utils.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMovies {

    private static ApiMovies apiMovies;
    private static Retrofit retrofit;
    public static final String BASE_URL = "https://api.themoviedb.org/";


    private ApiMovies() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ApiMovies getInstance() {
        if (apiMovies == null) {
            apiMovies = new ApiMovies();
        }
        return apiMovies;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }

}
