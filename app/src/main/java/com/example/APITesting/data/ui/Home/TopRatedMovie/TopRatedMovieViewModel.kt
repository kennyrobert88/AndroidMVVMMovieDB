package com.example.APITesting.data.ui.Home.TopRatedMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.APITesting.data.repository.NetworkState
import com.example.APITesting.data.Vo.Movie

class TopRatedMovieViewModel(private val movieRepository : TopRatedMoviePagedListRepository) : ViewModel() {

    fun  moviePagedList() : LiveData<PagedList<Movie>> = movieRepository.fetchLiveMoviePagedList()


    fun  networkState() : LiveData<NetworkState> = movieRepository.getNetworkState()


    fun listIsEmpty(): Boolean {
        return moviePagedList().value?.isEmpty() ?: true
    }
}