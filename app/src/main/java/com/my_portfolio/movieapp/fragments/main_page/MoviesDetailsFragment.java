package com.my_portfolio.movieapp.fragments.main_page;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.my_portfolio.movieapp.R;
import com.my_portfolio.movieapp.model.MainViewModel;
import com.my_portfolio.movieapp.utils.pojo.MovieResponseResult;
import com.squareup.picasso.Picasso;

public class MoviesDetailsFragment extends Fragment {

    private ImageView favorite;
    private ImageView bigPoster;
    private TextView title;
    private TextView releaseDate;
    private TextView description;
    private RatingBar rateBar;

    private int id;
    private MovieResponseResult movieResponseResult;
    private MainViewModel viewModel;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_details, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        rateBar = view.findViewById(R.id.rating_bar);

        rateBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    float touchPositionX = motionEvent.getX();
                    float width = rateBar.getWidth();
                    float starsf = (touchPositionX / width) * 5.0f;
                    float stars = (int)starsf + 1;
                    rateBar.setRating(stars);

                    Toast.makeText(requireActivity(), String.valueOf("test"), Toast.LENGTH_SHORT).show();
                    view.setPressed(false);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setPressed(true);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                    view.setPressed(false);
                }
                return true;
            }
        });

        favorite = view.findViewById(R.id.add_to_favorite);
        bigPoster = view.findViewById(R.id.ivBigPoster);
        title = view.findViewById(R.id.detailPageTitle);
        releaseDate = view.findViewById(R.id.release_date);
        description = view.findViewById(R.id.descriptionFilm);
        rateBar = view.findViewById(R.id.rating_bar);
        movieResponseResult = viewModel.getMovieById(id);
//        Bundle bundle = getArguments();
//        bundle.get("id");
//        Picasso.get().load(movieResponseResult.getPosterPath()).into(bigPoster);
//        title.setText(movieResponseResult.getTitle());
//        releaseDate.setText(movieResponseResult.getReleaseDate());
//        description.setText(movieResponseResult.getOverview());
        return view;
    }
}