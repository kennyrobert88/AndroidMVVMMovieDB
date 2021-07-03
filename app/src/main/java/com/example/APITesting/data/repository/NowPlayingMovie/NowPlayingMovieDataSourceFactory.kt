package com.example.APITesting.data.repository.NowPlayingMovie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.APITesting.data.api.TheMovieDBInterface
import com.example.APITesting.data.Vo.Movie

class NowPlayingMovieDataSourceFactory (private val apiService : TheMovieDBInterface)
: DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource =  MutableLiveData<NowPlayingMovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = NowPlayingMovieDataSource(apiService)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}