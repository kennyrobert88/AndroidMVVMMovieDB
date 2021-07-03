package com.example.APITesting.data.repository.TopRatedMovie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.APITesting.data.api.TheMovieDBInterface
import com.example.APITesting.data.Vo.Movie

// Hasil PagedList
class TopRatedMovieDataSourceFactory (private val apiService : TheMovieDBInterface)
    : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource =  MutableLiveData<TopRatedMovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = TopRatedMovieDataSource(apiService)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}