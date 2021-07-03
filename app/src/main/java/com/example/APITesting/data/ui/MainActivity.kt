package com.example.APITesting.data.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.APITesting.R
import com.example.APITesting.data.ui.Home.NowPlayingMovie.Adapter.NowPlayingMovieLimitPagedListAdapter
import com.example.APITesting.data.ui.Home.NowPlayingMovie.NowPlayingMovieViewModel
import com.example.APITesting.data.ui.Home.PopularMovie.Adapter.PopularMovieLimitPagedListAdapter
import com.example.APITesting.data.ui.Home.PopularMovie.PopularMovieViewModel
import com.example.APITesting.data.ui.Home.TopRatedMovie.Adapter.TopRatedMovieLimitPagedListAdapter
import com.example.APITesting.data.ui.Home.TopRatedMovie.TopRatedMovieViewModel
import kotlinx.android.synthetic.main.home_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_movie)

        val popularMovieViewModel: PopularMovieViewModel by viewModel()
        val topRatedViewModel: TopRatedMovieViewModel by viewModel()
        val nowPlayingMovieViewModel: NowPlayingMovieViewModel by viewModel()

        val popularMovieAdapter = PopularMovieLimitPagedListAdapter(this)
        val topRatedMovieAdapter = TopRatedMovieLimitPagedListAdapter(this)
        val nowPlayingMovieAdapter = NowPlayingMovieLimitPagedListAdapter(this)


        /*val gridLayoutManager = GridLayoutManager(this,3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = popularMovieAdapter.getItemViewType(position)
                if (viewType == popularMovieAdapter.MOVIE_VIEW_TYPE) return  1    // Movie_VIEW_TYPE will occupy 1 out of 3 span
                else return 3                                              // NETWORK_VIEW_TYPE will occupy all 3 span
            }
        };*/

        /*gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = topRatedMovieAdapter.getItemViewType(position)
                if (viewType == topRatedMovieAdapter.MOVIE_VIEW_TYPE) return  1    // Movie_VIEW_TYPE will occupy 1 out of 3 span
                else return 3                                              // NETWORK_VIEW_TYPE will occupy all 3 span
            }
        };

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = nowPlayingMovieAdapter.getItemViewType(position)
                if (viewType == nowPlayingMovieAdapter.MOVIE_VIEW_TYPE) return  1    // Movie_VIEW_TYPE will occupy 1 out of 3 span
                else return 3                                              // NETWORK_VIEW_TYPE will occupy all 3 span
            }
        };*/

        with(rv_popular_movie){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = popularMovieAdapter
        }

        with(rv_now_playing){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = nowPlayingMovieAdapter
        }

        with(rv_top_rated){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = topRatedMovieAdapter
        }

        /*hz_popular_movie.layoutManager = gridLayoutManager
        hz_popular_movie.setHasFixedSize(true)
        hz_popular_movie.adapter = popularMovieAdapter

        hz_now_playing_movie.layoutManager = gridLayoutManager
        hz_now_playing_movie.setHasFixedSize(true)
        hz_now_playing_movie.adapter = nowPlayingMovieAdapter

        hz_top_rated_movie.layoutManager = gridLayoutManager
        hz_top_rated_movie.setHasFixedSize(true)
        hz_top_rated_movie.adapter = topRatedMovieAdapter
        */
        popularMovieViewModel.moviePagedList().observe(this, Observer {
            popularMovieAdapter.submitList(it)
        })

        nowPlayingMovieViewModel.moviePagedList().observe(this, Observer {
            nowPlayingMovieAdapter.submitList(it)
        })

        topRatedViewModel.moviePagedList().observe(this, Observer {
            topRatedMovieAdapter.submitList(it)
        })
    /*
        popularMovieViewModel.networkState.observe(this, Observer {
            progress_bar_popular.visibility = if (popularMovieViewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error_popular.visibility = if (popularMovieViewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!popularMovieViewModel.listIsEmpty()) {
                popularMovieAdapter.setNetworkState(it)
            }
        })
    */
    }
}
