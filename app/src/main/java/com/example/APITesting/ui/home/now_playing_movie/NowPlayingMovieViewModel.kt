package com.example.APITesting.ui.home.now_playing_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.APITesting.data.repository.NetworkState
import com.example.APITesting.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable


class NowPlayingMovieViewModel(private val movieRepository: NowPlayingMoviePagedListRepository) : ViewModel() {
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
