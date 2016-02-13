package com.moheem.popularmovies.app;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.moheem.popularmovies.app.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Moheem Ilyas on 2/4/16.
 * <p/>
 * Revision Date: 2/8/16
 * Revised By:  Moheem Ilyas
 * Description: This custom adapter allows us to properly load the movie posters obtained through the themoviedb
 *              API request into ImageViews to display on screen.
 *
 * Notes: The JSON response from themoviedb request will return a JSON object that has a JSON array of the
 *        JSON objects that actually represent a movie, which is why the ArrayAdapter consists of MovieModel.Movie
 *        elements.
 */
public class MovieAdapter extends ArrayAdapter<MovieModel.Movie> {
    // Was going back and forth about having the poster size that will be used in the Uri passed to Picasso...
    // decided to make it an attribute for a "just in case" scenario where I might want to swap out based on the users
    // screen config.
    private String mSize;

    /**
     *
     * @param context
     * @param objects
     * @param size
     */
    public MovieAdapter(Context context, List<MovieModel.Movie> objects, String size) {
        super(context, 0, objects);
        this.mSize = size;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // Inflating the layout the contains the single ImageView (id = image).
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_image, parent, false);
        }

        /* Note to self, the next few linesreason this is outside the if block above is because we need
           to make sure that the poster image is loaded in EVERY time.
          */
        ImageView posterImage = (ImageView) convertView.findViewById(R.id.image);

        // Load the poster image of the Movie item at the current position into the ImageView that will be displayed.
        loadImage(getItem(position), posterImage);

        return convertView;
    }

    /**
     *
     * @param movie
     * @param posterImageView
     *
     * Description: Helper method to load the poster image into an ImageView using the Picasso API.
     */
    private void loadImage(MovieModel.Movie movie, ImageView posterImageView) {
        String base = "http://image.tmdb.org/t/p";

        // The movie posterPath attribute will have a leading forward slash. If we leave that in there, the Picasso
        // API call will not process successfully because there will be a %2f and then the poster path, i.e.
        // http://image.tmdb.org/t/p/%2fuETWtwsE1QjfoFqRQqFLnSjppPA.jpg
        String posterPath = movie.getPosterPath().replace("/", "");

        Uri uri = Uri.parse(base).buildUpon()
                .appendPath(this.mSize)
                .appendPath(posterPath).build();

        // The magic.
        Picasso.with(getContext()).load(uri).into(posterImageView);

    }
}
