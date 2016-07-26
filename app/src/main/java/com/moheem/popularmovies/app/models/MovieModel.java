package com.moheem.popularmovies.app.models;

import android.graphics.Movie;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Moheem Ilyas on 2/6/16.
 * <p/>
 * Revision Date: 2/7/16
 * Revised By: Moheem Ilyas
 * Description: POJO class for Retrofit to use to parse the JSON into a Java object.
 *
 * Notes: The SerializedName annotation argument must match the JSON attribute name in the response JSON
 *        object. This is also why we need the Movie inner class...the "results" attribute is NOT a JSON object but a JSON array
 *        whose elements are JSON objects (and so we need to specifiy how to convert the JSON object elements to a
 *        Java object). Particularly, the "results" attribute contains (array) items that actually represent the movie.
 */
public class MovieModel{

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Movie> results;

    @SerializedName("total_results")
    private Integer totalResults;

    @SerializedName("total_pages")
    private Integer totalPages;

    /*
    GETTERS AND SETTERS FOR MovieModel
     */


    /**
     *
     * @param page
     */
    public void setPage(int page){
        this.page = page;
    }

    /**
     *
     * @return
     */
    public int getPage(){
        return this.page;
    }



    /**
     *
     * @param results
     */
    public void setResults(List<Movie> results){
        this.results = results;
    }

    /**
     *
     * @return
     */
    public List<Movie> getResults(){
        return this.results;
    }



    /**
     *
     * @param totalResults
     */
    public void setTotalResults(Integer totalResults){
        this.totalResults = totalResults;
    }

    /**
     *
     * @return
     */
    public Integer getTotalResults() {
        return this.totalResults;
    }


    /**
     *
     * @param totalPages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     *
     * @return
     */
    public Integer getTotalPages() {
        return this.totalPages;
    }


    /**
     * Movie object that acts as the POJO for an item in the JSON array "results".
     *
     */
    public static class Movie implements Parcelable{
        @SerializedName("poster_path")
        private String posterPath;

        @SerializedName("adult")
        private boolean adult;

        @SerializedName("overview")
        private String overview;

        @SerializedName("release_date")
        private String releaseDate;

        @SerializedName("genre_ids")
        private List<Integer> genreIds;

        @SerializedName("id")
        private Integer id;

        @SerializedName("original_title")
        private String originalTitle;

        @SerializedName("original_language")
        private String originalLanguage;

        @SerializedName("title")
        private String title;

        @SerializedName("backdrop_path")
        private String backdropPath;

        @SerializedName("popularity")
        private Double popularity;

        @SerializedName("vote_count")
        private Integer voteCount;

        @SerializedName("video")
        private boolean video;

        @SerializedName("vote_average")
        private Double voteAverage;


        /*
        GETTERS AND SETTERS FOR Movie
         */

        /**
         *
         * @param posterPath
         */
        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        /**
         *
         * @return
         */
        public String getPosterPath() {
            return this.posterPath;
        }


        /**
         *
         * @param adult
         */
        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        /**
         *
         * @return
         */
        public boolean getAdult() {
            return this.adult;
        }


        /**
         *
         * @param overview
         */
        public void setOverview(String overview) {
            this.overview = overview;
        }

        /**
         *
         * @return
         */
        public String getOverview() {
            return this.overview;
        }



        /**
         *
         * @param releaseDate
         */
        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        /**
         *
         * @return
         */
        public String getReleaseDate() {
            return this.releaseDate;
        }



        /**
         *
         * @param genreIds
         */
        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }

        /**
         *
         * @return
         */
        public List<Integer> getGenreIds() {
            return this.genreIds;
        }


        /**
         *
         * @param id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         */
        public Integer getId() {
            return this.id;
        }



        /**
         *
         * @param originalTitle
         */
        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        /**
         *
         * @return
         */
        public String getOriginalTitle() {
            return this.originalTitle;
        }


        /**
         *
         * @param originalLanguage
         */
        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        /**
         *
         * @return
         */
        public String getOriginalLanguage() {
            return this.originalLanguage;
        }


        /**
         *
         * @param title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         *
         * @return
         */
        public String getTitle() {
            return this.title;
        }


        /**
         *
         * @param backdropPath
         */
        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        /**
         *
         * @return
         */
        public String getBackdropPath() {
            return this.backdropPath;
        }


        /**
         *
         * @param popularity
         */
        public void setPopularity(Double popularity) {
            this.popularity = popularity;
        }

        /**
         *
         * @return
         */
        public Double getPopularity(){
            return this.popularity;
        }


        /**
         *
         * @param voteCount
         */
        public void setVoteCount(Integer voteCount){
            this.voteCount = voteCount;
        }

        /**
         *
         * @return
         */
        public Integer getVoteCount(){
            return this.voteCount;
        }

        /**
         *
         * @param video
         */
        public void setVideo(boolean video){
            this.video = video;
        }

        /**
         *
         * @return
         */
        public boolean getVideo() {
            return this.video;
        }

        /**
         *
         * @param voteAverage
         */
        public void setVoteAverage(Double voteAverage){
            this.voteAverage = voteAverage;
        }

        /**
         *
         * @return
         */
        public Double getVoteAverage() {
            return this.voteAverage;
        }

        /*
        PARCELABLE RELATED MATERIAL
         */

        private Movie(Parcel in){
            posterPath = in.readString();
            // http://stackoverflow.com/questions/6201311/how-to-read-write-a-boolean-when-implementing-the-parcelable-interface
            adult = (Boolean) in.readValue(null);
            overview = in.readString();
            releaseDate = in.readString();
            /* THIS COULD BE A PROBLEM BECAUSE GENEREIDS IS A LIST */
            genreIds = in.readArrayList(Integer.class.getClassLoader());
            id = in.readInt();
            originalTitle = in.readString();
            originalLanguage = in.readString();
            title = in.readString();
            backdropPath = in.readString();
            popularity = in.readDouble();
            voteCount = in.readInt();
            video = (Boolean) in.readValue(null);
            voteAverage = in.readDouble();

        }

        // Not doing anything with this.
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(posterPath);
            dest.writeValue(adult);
            dest.writeString(overview);
            dest.writeString(releaseDate);
            dest.writeList(genreIds);
            dest.writeInt(id);
            dest.writeString(originalTitle);
            dest.writeString(originalLanguage);
            dest.writeString(title);
            dest.writeString(backdropPath);
            dest.writeDouble(popularity);
            dest.writeInt(voteCount);
            dest.writeValue(video);
            dest.writeDouble(voteAverage);

        }

        public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
            @Override
            public Movie createFromParcel(Parcel source) {
                return new Movie(source);
            }

            @Override
            public Movie[] newArray(int size) {
                return new Movie[size];
            }
        };
    }
}
