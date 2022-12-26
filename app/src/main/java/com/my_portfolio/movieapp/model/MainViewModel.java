package com.my_portfolio.movieapp.model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.MainThread;
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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private static MovieDatabase database;
    private LiveData<List<MovieResponseResult>> movies;
    private CompositeDisposable compositeDisposable;

    public LiveData<List<MovieResponseResult>> getMovies() {
        return movies;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = MovieDatabase.getInstance(application);
        movies = database.movieDao().getAllMovies();
    }

    @SuppressWarnings("unchecked")
    private void insertMovies(List<MovieResponseResult> movies) {
        new InsertMoviesTask().execute(movies);
    }

    private static class InsertMoviesTask extends AsyncTask<List<MovieResponseResult>, Void, Void> {

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<MovieResponseResult>... lists) {
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
        compositeDisposable = new CompositeDisposable();
        Disposable disposable = apiService.getMovies("caacd00da84fc961add94db204602991","ru-RU","popularity.desc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {
                        deleteAllMovies();
                        insertMovies(movieResponse.getResults());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}
