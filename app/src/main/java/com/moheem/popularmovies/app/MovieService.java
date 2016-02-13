package com.moheem.popularmovies.app;

import com.moheem.popularmovies.app.models.MovieModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Moheem Ilyas on 2/7/16.
 * <p/>
 * Revision Date: 2/8/16
 * Revised By: Moheem Ilyas
 * Description: Provides Retrofit with a service so that it can make an API request to themoviedb.org.
 *
 * Notes: The query annotation argument must match what the actual query string is in the API.
 *        For example, for themoviedb, the call is something like
 *        https://api.themoviedb.org/3/movie/550?api_key=82919289123jlkjl
 *        So to add the query parameter for the api key, the query annotation argument must be "api_key".
 */
public final class MovieService {
    public interface MOVIEAPI {
        @GET("3/movie/{sort-by}")
        Call<MovieModel> getMovies(
                // Since the user will be allowed to change the sort order via a setting (highest rated or most popular),
                // we will use the "sort-by" replacement block so that fragments that use this interface can manupilate
                // the request base on shared preferences.
                @Path("sort-by") String sortBy,
                @Query("api_key") String key,
                // For now, we will only deal with one page, but parameter is here for a "just in case" scenario".
                @Query("page") String pageNumber
        );
    }
}
