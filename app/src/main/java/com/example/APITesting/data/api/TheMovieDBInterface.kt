package com.example.APITesting.data.api

import com.example.APITesting.data.vo.MovieDetails
import com.example.APITesting.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
// Declaration of getPopularMovie and getMovieDetails
interface TheMovieDBInterface {

    // https://api.themoviedb.org/3/movie/popular?api_key=6e63c2317fbe963d76c3bdc2b785f6d1&page=1
    // https://api.themoviedb.org/3/movie/299534?api_key=6e63c2317fbe963d76c3bdc2b785f6d1
    // https://api.themoviedb.org/3/

    // goes to movieDataSource
    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    //
    @GET("movie/top_rated")
    fun getTopRatedMovie(@Query("page") page: Int): Single<MovieResponse>
    //
    @GET("movie/now_playing")
    fun getNowPlayingMovie(@Query("page") page: Int): Single<MovieResponse>
    //
    // Goes to movieDetailsNetworkDataSource
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}