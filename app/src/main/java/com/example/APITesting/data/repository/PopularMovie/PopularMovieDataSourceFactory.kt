package com.example.APITesting.data.repository.PopularMovie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.APITesting.data.api.TheMovieDBInterface
import com.example.APITesting.data.Vo.Movie

class PopularMovieDataSourceFactory (private val apiService : TheMovieDBInterface)
    : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource =  MutableLiveData<PopularMovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = PopularMovieDataSource(apiService)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
        }
    }