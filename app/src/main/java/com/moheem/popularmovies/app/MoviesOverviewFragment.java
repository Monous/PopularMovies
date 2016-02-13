package com.moheem.popularmovies.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.moheem.popularmovies.app.models.MovieModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;

/**
 * Created by Moheem Ilyas on 2/7/16.
 * <p/>
 * Revision Date: 2/8/16
 * Revised By: Moheem Ilyas
 * Description: This fragment will be responsible for connecting the API requests (via Picasso and themoviedb) to
 *              displaying the posters on screen by making use of Retrofit for network calls. Step aside AsyncTask.
 */
public class MoviesOverviewFragment extends Fragment {

    private static MovieAdapter mMovieAdapter;
    private Call<MovieModel> mMovieCall;

    public MoviesOverviewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onStart() {
        super.onStart();
        updateAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies_overview, container, false);

        // Initialize the movie adapter to steer clear of a NullPointer. Juxtaposition intended.
        mMovieAdapter = new MovieAdapter(getActivity(), new ArrayList<MovieModel.Movie>(), "w185");


        GridView gridView = (GridView) rootView.findViewById(R.id.movies_gridview);
        gridView.setAdapter(mMovieAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent movieDetailIntent = MovieDetailActivity.makeIntent(getActivity(),
                        mMovieAdapter.getItem(position));
                startActivity(movieDetailIntent);
            }
        });

        return rootView;
    }

    private void updateAdapter(){
        /*
        Getting the sort order (popular or top_rated) based on the user's stored Preference.
         */
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = sharedPreferences.getString(getString(R.string.pref_sort_order_key),
                getString(R.string.pref_sort_order_default));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService.MOVIEAPI movieApi = retrofit.create(MovieService.MOVIEAPI.class);

        mMovieCall = movieApi.getMovies(sortOrder, BuildConfig.THE_MOVIE_DB_API_KEY, "1");

        // Note to self, this is making a ASYNCHRONOUS call.
        mMovieCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, retrofit2.Response<MovieModel> response) {
                mMovieAdapter.clear();
                // Setting the adapters data to the List of Movie items. Note to self, response.body() will yield the
                // PAGE information.
                mMovieAdapter.addAll(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
