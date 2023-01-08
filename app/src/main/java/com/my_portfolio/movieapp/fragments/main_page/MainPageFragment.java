package com.my_portfolio.movieapp.fragments.main_page;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.my_portfolio.movieapp.R;
import com.my_portfolio.movieapp.adapter.ParentRvAdapter;
import com.my_portfolio.movieapp.model.MainViewModel;
import com.my_portfolio.movieapp.utils.pojo.MovieResponse;
import com.my_portfolio.movieapp.utils.pojo.MovieResponseResult;

import java.util.List;

public class MainPageFragment extends Fragment {

    private RecyclerView rvPosters;
    private ParentRvAdapter adapter;

    private MainViewModel mainViewModel;
    private int numOfColumns = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        rvPosters = view.findViewById(R.id.movies_page);
        rvPosters.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new ParentRvAdapter();
        rvPosters.setAdapter(adapter);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        adapter.setOnPosterClickListener(new ParentRvAdapter.OnPosterClickListener() {
            @Override
            public void onPosterClick(int position) {
                MovieResponseResult movieResponseResult = adapter.getMovieResponseResults().get(position);
                MoviesDetailsFragment moviesDetailsFragment = new MoviesDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id",movieResponseResult.getId());
                moviesDetailsFragment.setArguments(bundle);
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_page_container,moviesDetailsFragment);
                ft.addToBackStack(moviesDetailsFragment.toString());
                ft.commit();
            }
        });

        mainViewModel.getMovies().observe(requireActivity(), new Observer<List<MovieResponseResult>>() {
            @Override
            public void onChanged(List<MovieResponseResult> movieResponseResults) {
                if (movieResponseResults != null) {
                    adapter.setMovieResponseResults(movieResponseResults);
                }
            }
        });
        mainViewModel.loadData();

        return view;
    }
}