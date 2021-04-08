package com.example.APITesting.data.vo


import com.google.gson.annotations.SerializedName
// Used in MovieDataSource, MovieDataSourceFactory, MovieResponse, MainActivityViewModel, MoviePagedListRepository, PopularMoviePagedListAdapter
data class Movie(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String
)