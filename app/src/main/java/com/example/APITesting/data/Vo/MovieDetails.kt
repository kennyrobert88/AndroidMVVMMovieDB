package com.example.APITesting.data.Vo


import com.google.gson.annotations.SerializedName

// Used in TheMovieDBInterface, MovieDetailsNetworkDataSource, MoviesDetailRepository, SingleMovie, SingleMovieViewModel
data class MovieDetails(
    val budget: Int,
    val id: Int,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val rating: Double
)