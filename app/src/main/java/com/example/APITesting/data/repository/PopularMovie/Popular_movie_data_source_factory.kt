package com.example.APITesting.data.repository.PopularMovie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.APITesting.data.api.TheMovieDBInterface
import com.example.APITesting.data.repository.NowPlayingMovie.now_playing_movie_data_source
import com.example.APITesting.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class Popular_movie_data_source_factory (private val apiService : TheMovieDBInterface, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource =  MutableLiveData<popular_movie_data_source>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = popular_movie_data_source(apiService, compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
        }
    }