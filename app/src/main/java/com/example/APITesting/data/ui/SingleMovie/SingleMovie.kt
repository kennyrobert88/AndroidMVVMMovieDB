package com.example.APITesting.data.ui.SingleMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.APITesting.R
import com.example.APITesting.data.api.POSTER_BASE_URL
import com.example.APITesting.data.repository.NetworkState
import com.example.APITesting.data.Vo.MovieDetails
import kotlinx.android.synthetic.main.activity_single_movie.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.*

class SingleMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val singleMovieViewModel: SingleMovieViewModel by viewModel()
        val movieId: Int = intent.getIntExtra("id",1)
        singleMovieViewModel.movieId = movieId.toString()

        singleMovieViewModel.movieDetails().observe(this, Observer {
            bindUI(it)
        })

        singleMovieViewModel.networkState().observe(this, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE

        })

    }

    fun bindUI( it: MovieDetails) {
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(it.budget)
        movie_revenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath

        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);
    }
}
