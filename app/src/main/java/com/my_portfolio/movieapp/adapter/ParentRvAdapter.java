package com.my_portfolio.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my_portfolio.movieapp.R;
import com.my_portfolio.movieapp.utils.pojo.MovieResponseResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ParentRvAdapter extends RecyclerView.Adapter<ParentRvAdapter.ParentViewHolder> {

    private Context context;
    private List<MovieResponseResult> movieResponseResults;
    private OnPosterClickListener onPosterClickListener;

    public ParentRvAdapter(Context context,List<MovieResponseResult> movieResponseResults) {
        this.context = context;
        this.movieResponseResults = movieResponseResults;
    }

    public List<MovieResponseResult> getMovieResponseResults() {
        return movieResponseResults;
    }

    public void setMovieResponseResults(List<MovieResponseResult> movieResponseResults) {
        this.movieResponseResults = movieResponseResults;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ParentRvAdapter.ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_movies,parent,false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {
        MovieResponseResult movieResponseResult = movieResponseResults.get(position);
        Picasso.get().load(movieResponseResult.getPosterPath()).into(holder.ivSmallPoster);
        holder.tvTitle.setText(movieResponseResult.getTitle());
        holder.tvReleaseDate.setText(movieResponseResult.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        if (this.movieResponseResults != null) {
            return movieResponseResults.size();
        }
        return 0;
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivSmallPoster;
        private TextView tvTitle;
        private TextView tvReleaseDate;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);

            ivSmallPoster = itemView.findViewById(R.id.ivSmallPoster);
            tvTitle = itemView.findViewById(R.id.tvName);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onPosterClickListener != null) {
                        onPosterClickListener.onPosterClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnPosterClickListener {
        void onPosterClick(int position);
    }
}
