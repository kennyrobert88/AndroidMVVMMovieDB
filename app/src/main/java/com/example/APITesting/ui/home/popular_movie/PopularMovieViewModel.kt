package com.example.APITesting.ui.home.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.APITesting.data.repository.NetworkState
import com.example.APITesting.data.vo.Movie
import com.example.APITesting.ui.home.popular_movie.PopularMoviePagedListRepository
import io.reactivex.disposables.CompositeDisposable

class PopularMovieViewModel(private val movieRepository : PopularMoviePagedListRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()



    val  moviePagedList : LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val  networkState : LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}