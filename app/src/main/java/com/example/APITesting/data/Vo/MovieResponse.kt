package com.example.APITesting.data.Vo


import com.google.gson.annotations.SerializedName

// Used in TheMovieDBInterface
data class MovieResponse(
    val page: Int,
    @SerializedName("results")
    val movieList: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)