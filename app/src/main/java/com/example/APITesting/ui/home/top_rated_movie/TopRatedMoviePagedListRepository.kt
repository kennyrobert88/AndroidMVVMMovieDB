package com.example.APITesting.ui.home.top_rated_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.APITesting.data.api.POST_PER_PAGE
import com.example.APITesting.data.api.TheMovieDBInterface
import com.example.APITesting.data.repository.NetworkState
import com.example.APITesting.data.repository.PopularMovie.Popular_movie_data_source_factory
import com.example.APITesting.data.repository.PopularMovie.popular_movie_data_source
import com.example.APITesting.data.repository.TopRatedMovie.Top_rated_movie_data_source_factory
import com.example.APITesting.data.repository.TopRatedMovie.top_rated_movie_data_source
import com.example.APITesting.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class TopRatedMoviePagedListRepository (private val apiService : TheMovieDBInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: Top_rated_movie_data_source_factory

    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = Top_rated_movie_data_source_factory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<top_rated_movie_data_source, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, top_rated_movie_data_source::networkState
        )
    }
}