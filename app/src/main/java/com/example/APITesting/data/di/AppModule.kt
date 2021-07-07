package com.example.APITesting.data.di

import com.example.APITesting.data.api.BASE_URL
import com.example.APITesting.data.api.TheMovieDBInterface
import com.example.APITesting.data.repository.NowPlayingMovie.NowPlayingMovieDataSource
import com.example.APITesting.data.repository.NowPlayingMovie.NowPlayingMovieDataSourceFactory
import com.example.APITesting.data.repository.PopularMovie.PopularMovieDataSource
import com.example.APITesting.data.repository.PopularMovie.PopularMovieDataSourceFactory
import com.example.APITesting.data.repository.SingleMovie.SingleMovieDataSource
import com.example.APITesting.data.repository.TopRatedMovie.TopRatedMovieDataSource
import com.example.APITesting.data.repository.TopRatedMovie.TopRatedMovieDataSourceFactory
import com.example.APITesting.data.ui.Home.NowPlayingMovie.NowPlayingMoviePagedListRepository
import com.example.APITesting.data.ui.Home.NowPlayingMovie.NowPlayingMovieViewModel
import com.example.APITesting.data.ui.Home.PopularMovie.PopularMoviePagedListRepository
import com.example.APITesting.data.ui.Home.PopularMovie.PopularMovieViewModel
import com.example.APITesting.data.ui.Home.TopRatedMovie.TopRatedMoviePagedListRepository
import com.example.APITesting.data.ui.Home.TopRatedMovie.TopRatedMovieViewModel
import com.example.APITesting.data.ui.SingleMovie.SingleMovieRepository
import com.example.APITesting.data.ui.SingleMovie.SingleMovieViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(TheMovieDBInterface::class.java)
    }
}

val dataSourceModule = module {
    single {
        SingleMovieDataSource(get())
    }
    single {
        NowPlayingMovieDataSource(get())
    }
    single {
        PopularMovieDataSource(get())
    }
    single {
        TopRatedMovieDataSource(get())
    }
}

val dataSourceFactoryModule = module {
    single {
        NowPlayingMovieDataSourceFactory(get())
    }
    single {
        PopularMovieDataSourceFactory(get())
    }
    single {
        TopRatedMovieDataSourceFactory(get())
    }
}

val repositoryModule = module {
    single {
        SingleMovieRepository(get())
    }
    single {
        TopRatedMoviePagedListRepository(get())
    }
    single {
        NowPlayingMoviePagedListRepository(get())
    }
    single {
        PopularMoviePagedListRepository(get())
    }
}

val viewModelModule = module {
    viewModel {
        SingleMovieViewModel(get())
    }
    viewModel {
        PopularMovieViewModel(get())
    }
    viewModel {
        NowPlayingMovieViewModel(get())
    }
    viewModel {
        TopRatedMovieViewModel(get())
    }
}