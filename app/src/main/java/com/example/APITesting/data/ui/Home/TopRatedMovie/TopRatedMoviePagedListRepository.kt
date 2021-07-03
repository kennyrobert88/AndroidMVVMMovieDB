package com.example.APITesting.data.ui.Home.TopRatedMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.APITesting.data.api.POST_PER_PAGE
import com.example.APITesting.data.api.TheMovieDBInterface
import com.example.APITesting.data.repository.NetworkState
import com.example.APITesting.data.repository.TopRatedMovie.TopRatedMovieDataSource
import com.example.APITesting.data.repository.TopRatedMovie.TopRatedMovieDataSourceFactory
import com.example.APITesting.data.Vo.Movie

class TopRatedMoviePagedListRepository (private val apiService : TheMovieDBInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: TopRatedMovieDataSourceFactory

    fun fetchLiveMoviePagedList () : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = TopRatedMovieDataSourceFactory(apiService)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<TopRatedMovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, TopRatedMovieDataSource::networkState
        )
    }
}