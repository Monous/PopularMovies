package com.moheem.popularmovies.app;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.moheem.popularmovies.app.models.MovieModel;
import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;

/**
 * Created by Moheem Ilyas on 2/8/16.
 * <p/>
 * Revision Date: 2/11/16
 * Revised By: Moheem Ilyas
 * Description: Displays the details of the Movie that the user clicked on in the MoviesOverviewActivity.
 *              Specifically, MovieDetailFragment displays the movie title, poster image, release date, vote average,
 *              and overview.
 */
public class MovieDetailFragment extends Fragment {
    private static final String ARGUMENT_MOVIE_KEY = "com.moheem.popularmovies.app.MovieDetailFragment";
    private MovieModel.Movie mMovie;

    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_movie_detail, container, false);

        mMovie = getArguments().getParcelable(ARGUMENT_MOVIE_KEY);

        /*
        Getting all the widgets from the layout in order to set their necessary attributes to the movie details.
         */
        TextView movieTitle = (TextView) rootView.findViewById(R.id.movie_detail_title);
        ImageView posterImage = (ImageView) rootView.findViewById(R.id.movie_detail_poster);
        TextView releaseDate = (TextView) rootView.findViewById(R.id.movie_detail_release_date);
        TextView voteAverage = (TextView) rootView.findViewById(R.id.movie_detail_vote_average);
        TextView plotSynopsis = (TextView) rootView.findViewById(R.id.movie_detail_plot_synopsis);

        /*
        Setting all of the widgets to display movie details.
         */
        movieTitle.setText(mMovie.getTitle());
        loadImage(mMovie, posterImage, "w185");
        releaseDate.setText(mMovie.getReleaseDate());
        voteAverage.setText(mMovie.getVoteAverage().toString());
        plotSynopsis.setText(mMovie.getOverview());

        return rootView;
    }

    /**
     * Description: Returns the key for onCreateView to use to extract the Parcelable. Although this might look like
     *              I was aiming for encapsulation, my real goal was abstraction...having standalone components is
     *              a lovely thing.
     * @return
     */
    public static final String getArgumentMovieKey(){
        return ARGUMENT_MOVIE_KEY;
    }

    /**
     *
     * Description: Helper method to load the poster image into an ImageView using the Picasso API.
     *
     * @param movie
     * @param posterImageView
     */
    private void loadImage(MovieModel.Movie movie, ImageView posterImageView, String size) {
        String base = "http://image.tmdb.org/t/p";

        // The movie posterPath attribute will have a leading forward slash. If we leave that in there, the Picasso
        // API call will not process successfully because there will be a %2f and then the poster path, i.e.
        // http://image.tmdb.org/t/p/%2fuETWtwsE1QjfoFqRQqFLnSjppPA.jpg
        String posterPath = movie.getPosterPath().replace("/", "");

        Uri uri = Uri.parse(base).buildUpon()
                .appendPath(size)
                .appendPath(posterPath).build();

        // The magic.
        Picasso.with(getContext()).load(uri).into(posterImageView);

    }
}
