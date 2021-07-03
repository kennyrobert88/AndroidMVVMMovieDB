package com.example.APITesting.data.api

import com.example.APITesting.data.Vo.MovieDetails
import com.example.APITesting.data.Vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "6e63c2317fbe963d76c3bdc2b785f6d1"
const val BASE_URL = "https://api.themoviedb.org/3/"

const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 1
// Declaration of getPopularMovie and getMovieDetails
interface TheMovieDBInterface {

    // goes to movieDataSource
    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int, @Query("api_key") apiKey: String): Single<MovieResponse>

    //
    @GET("movie/top_rated")
    fun getTopRatedMovie(@Query("page") page: Int, @Query("api_key") apiKey: String): Single<MovieResponse>
    //
    @GET("movie/now_playing")
    fun getNowPlayingMovie(@Query("page") page: Int, @Query("api_key") apiKey: String): Single<MovieResponse>
    // Goes to movieDetailsNetworkDataSource
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Single<MovieDetails>
}