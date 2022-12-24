package com.my_portfolio.movieapp.model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.my_portfolio.movieapp.data.movie_data.MovieDatabase;
import com.my_portfolio.movieapp.utils.api.ApiMovies;
import com.my_portfolio.movieapp.utils.api.ApiService;
import com.my_portfolio.movieapp.utils.pojo.MovieResponse;
import com.my_portfolio.movieapp.utils.pojo.MovieResponseResult;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private static MovieDatabase database;
    private MutableLiveData<List<MovieResponseResult>> movies = new MutableLiveData<>();
//    private CompositeDisposable compositeDisposable;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = MovieDatabase.getInstance(application);
        movies = new MutableLiveData<>();
    }

    public MutableLiveData<List<MovieResponseResult>> getMovies() {
        return movies;
    }

    public void setMovies(MutableLiveData<List<MovieResponseResult>> movies) {
        this.movies = movies;
    }

    private void insertMovies(List<MovieResponseResult> movies) {
        new InsertMoviesTask().execute(movies);
    }

    private static class InsertMoviesTask extends AsyncTask<List<MovieResponseResult>, Void, Void> {

        @Override
        protected Void doInBackground(List<MovieResponseResult>... lists) {
            if (lists != null && lists.length > 0) {
                database.movieDao().insertMovies(lists[0]);
            }
            return null;
        }
    }

    private void deleteAllMovies() {
        new InsertMoviesTask().execute();
    }

    private static class DeleteAllMoviesTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            database.movieDao().deleteMovies();
            return null;
        }
    }

    public void loadData() {
        ApiMovies apiMovies = ApiMovies.getInstance();
        ApiService apiService = apiMovies.getApiService();
        Call<List<MovieResponseResult>> call = apiService.getMovies("caacd00da84fc961add94db204602991","ru-RU","popularity.desc");
        call.enqueue(new Callback<List<MovieResponseResult>>() {
            @Override
            public void onResponse(Call<List<MovieResponseResult>> call, Response<List<MovieResponseResult>> response) {
                Log.d("response", "onResponse: " + response.body());
                movies.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MovieResponseResult>> call, Throwable t) {
                Log.d("response", "onFailure: ");
                movies.postValue(null);
            }
        });
    }

//    @Override
//    protected void onCleared() {
//        compositeDisposable.dispose();
//        super.onCleared();
//    }
}
