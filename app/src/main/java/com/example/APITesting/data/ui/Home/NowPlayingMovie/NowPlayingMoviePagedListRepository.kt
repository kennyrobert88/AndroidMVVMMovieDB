package com.example.APITesting.data.ui.Home.NowPlayingMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.APITesting.data.api.POST_PER_PAGE
import com.example.APITesting.data.api.TheMovieDBInterface
import com.example.APITesting.data.repository.NetworkState
import com.example.APITesting.data.repository.NowPlayingMovie.NowPlayingMovieDataSource
import com.example.APITesting.data.repository.NowPlayingMovie.NowPlayingMovieDataSourceFactory

import com.example.APITesting.data.Vo.Movie

class NowPlayingMoviePagedListRepository (private val apiService : TheMovieDBInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: NowPlayingMovieDataSourceFactory

    fun fetchLiveMoviePagedList () : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = NowPlayingMovieDataSourceFactory(apiService)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

       fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<NowPlayingMovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, NowPlayingMovieDataSource::networkState
        )
    }
}