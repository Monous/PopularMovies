package com.moheem.popularmovies.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.moheem.popularmovies.app.models.MovieModel;

/**
 * Created by Moheem Ilyas on 2/9/16.
 * <p/>
 * Revision Date: 2/10/16
 * Revised By: Moheem Ilyas
 * Description: The MovieDetailActivity hosts the MovieDetailFragment. Currently, this class has limited functionality.
 *
 */
public class MovieDetailActivity extends ActionBarActivity {
    private static final String EXTRA_MOVIE = "com.moheem.popularmovies.app.MovieDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        /*
        Receive the intent, extract the Movie Parcelable (the movie the user clicked on), create a Bundle,
        set it to a new instance of MovieDetailFragment's arguments, start the fragment.
         */
        if (getIntent() != null){
            MovieModel.Movie movie = getIntent().getParcelableExtra(this.EXTRA_MOVIE);
            Bundle movieDetailFragBundle = new Bundle();
            // To understand why I used a static method to obtain the key, please refer to MovieDetailFragment.java.
            movieDetailFragBundle.putParcelable(MovieDetailFragment.getArgumentMovieKey(),
                    movie);

            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            movieDetailFragment.setArguments(movieDetailFragBundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, movieDetailFragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Description: Allows other activities to send an intent to this on without having to know what key to use.
     *              In other words, this is my attempt to further abstraction.
     * @param context
     * @param movie
     * @return
     */
    public static Intent makeIntent(Context context, MovieModel.Movie movie){
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}
