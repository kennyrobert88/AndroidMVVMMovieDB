package com.example.APITesting.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.APITesting.R
import com.example.APITesting.data.api.TheMovieDBClient
import com.example.APITesting.data.api.TheMovieDBInterface
import com.example.APITesting.data.repository.NetworkState
import com.example.APITesting.ui.home.now_playing_movie.Adapter.NowPlayingMovieLimitPagedListAdapter
import com.example.APITesting.ui.home.now_playing_movie.NowPlayingMoviePagedListRepository
import com.example.APITesting.ui.home.now_playing_movie.NowPlayingMovieViewModel
import com.example.APITesting.ui.home.popular_movie.PopularMovieViewModel
import com.example.APITesting.ui.home.popular_movie.PopularMoviePagedListRepository
import com.example.APITesting.ui.home.popular_movie.Adapter.PopularMovieLimitPagedListAdapter
import com.example.APITesting.ui.home.top_rated_movie.Adapter.TopRatedMovieLimitPagedListAdapter
import com.example.APITesting.ui.home.top_rated_movie.TopRatedMoviePagedListRepository
import com.example.APITesting.ui.home.top_rated_movie.TopRatedMovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_movie.*
import java.util.EnumSet.of

class MainActivity : AppCompatActivity() {

    private lateinit var popularMovieViewModel: PopularMovieViewModel
    private lateinit var topRatedViewModel : TopRatedMovieViewModel
    private lateinit var nowPlayingMovieViewModel: NowPlayingMovieViewModel

    lateinit var popularMovieRepository: PopularMoviePagedListRepository
    lateinit var topRatedMovieRepository: TopRatedMoviePagedListRepository
    lateinit var nowPlayingMovieRepository: NowPlayingMoviePagedListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_movie)

        val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()

        popularMovieRepository = PopularMoviePagedListRepository(apiService)
        topRatedMovieRepository = TopRatedMoviePagedListRepository(apiService)
        nowPlayingMovieRepository = NowPlayingMoviePagedListRepository(apiService)

        popularMovieViewModel = getPopularViewModel()
        topRatedViewModel = getTopRatedViewModel()
        nowPlayingMovieViewModel = getNowPlayingViewModel()


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
        popularMovieViewModel.moviePagedList.observe(this, Observer {
            popularMovieAdapter.submitList(it)
        })

        nowPlayingMovieViewModel.moviePagedList.observe(this, Observer {
            nowPlayingMovieAdapter.submitList(it)
        })

        topRatedViewModel.moviePagedList.observe(this, Observer {
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


    private fun getPopularViewModel(): PopularMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return PopularMovieViewModel(popularMovieRepository) as T
            }
        })[PopularMovieViewModel::class.java]
    }

    private fun getTopRatedViewModel(): TopRatedMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TopRatedMovieViewModel(topRatedMovieRepository) as T
            }
        })[TopRatedMovieViewModel::class.java]
    }

    private fun getNowPlayingViewModel(): NowPlayingMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return NowPlayingMovieViewModel(nowPlayingMovieRepository) as T
            }
        })[NowPlayingMovieViewModel::class.java]
    }
}
