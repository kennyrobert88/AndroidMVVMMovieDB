package com.example.APITesting.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.APITesting.R
import com.example.APITesting.data.api.TheMovieDBClient
import com.example.APITesting.data.api.TheMovieDBInterface
import com.example.APITesting.data.repository.NetworkState
import com.example.APITesting.ui.home.popular_movie.Adapter.PopularMovieLimitPagedListAdapter
import com.example.APITesting.ui.home.popular_movie.PopularMoviePagedListRepository
import com.example.APITesting.ui.home.popular_movie.PopularMovieViewModel
import com.example.APITesting.ui.home.top_rated_movie.Adapter.TopRatedMovieLimitPagedListAdapter
import com.example.APITesting.ui.home.top_rated_movie.Adapter.TopRatedMoviePagedListAdapter
import com.example.APITesting.ui.home.top_rated_movie.TopRatedMoviePagedListRepository
import com.example.APITesting.ui.home.top_rated_movie.TopRatedMovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class TopRatedMovie: AppCompatActivity() {
    private lateinit var viewModel: TopRatedMovieViewModel
    private lateinit var movieRepository: TopRatedMoviePagedListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()

        movieRepository = TopRatedMoviePagedListRepository(apiService)

        viewModel = getViewModel()

        val movieAdapter = TopRatedMoviePagedListAdapter(this)


        val gridLayoutManager = GridLayoutManager(this, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if (viewType == movieAdapter.MOVIE_VIEW_TYPE) return  1    // Movie_VIEW_TYPE will occupy 1 out of 3 span
                else return 3                                              // NETWORK_VIEW_TYPE will occupy all 3 span
            }
        };


        rv_movie_list.layoutManager = gridLayoutManager
        rv_movie_list.setHasFixedSize(true)
        rv_movie_list.adapter = movieAdapter

        viewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar_popular.visibility = if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error_popular.visibility = if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!viewModel.listIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })
    }

    private fun getViewModel(): TopRatedMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TopRatedMovieViewModel(movieRepository) as T
            }
        })[TopRatedMovieViewModel::class.java]
    }
}