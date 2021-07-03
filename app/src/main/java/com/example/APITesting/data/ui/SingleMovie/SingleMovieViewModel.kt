package com.example.APITesting.data.ui.SingleMovie

import androidx.lifecycle.ViewModel

class SingleMovieViewModel (private val movieRepository : SingleMovieRepository)  : ViewModel() {

    lateinit var movieId : String

    fun  movieDetails() = movieRepository.fetchSingleMovieDetails(movieId.toInt())

    fun networkState() = movieRepository.getMovieDetailsNetworkState()
}