package com.example.APITesting.data.ui.SingleMovie

import androidx.lifecycle.LiveData
import com.example.APITesting.data.api.TheMovieDBInterface
import com.example.APITesting.data.repository.SingleMovie.SingleMovieDataSource
import com.example.APITesting.data.repository.NetworkState
import com.example.APITesting.data.Vo.MovieDetails

class SingleMovieRepository (private val apiService : TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: SingleMovieDataSource

    fun fetchSingleMovieDetails (movieId: Int) : LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = SingleMovieDataSource(apiService)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}