package bobby.irawan.movieapp.di

import androidx.room.Room
import bobby.irawan.movieapp.AppDatabase
import bobby.irawan.movieapp.BuildConfig.BASE_URL
import bobby.irawan.movieapp.data.interceptor.HeaderInterceptor
import bobby.irawan.movieapp.data.movies.MoviesServiceApi
import bobby.irawan.movieapp.data.movies.MoviesService
import bobby.irawan.movieapp.data.movies.MoviesServiceImpl
import bobby.irawan.movieapp.utils.Constants.HEADER_INTERCEPTOR
import bobby.irawan.movieapp.utils.Constants.LOGGING_INTERCEPTOR
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by bobbyirawan09 on 13/08/20.
 */

val dataModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "favorite_database")
            .build()
    }

    single {
        get<AppDatabase>().favoriteDao()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(qualifier = named(LOGGING_INTERCEPTOR)))
            .addInterceptor(get<Interceptor>(qualifier = named(HEADER_INTERCEPTOR)))
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    single<Interceptor>(named(HEADER_INTERCEPTOR)) {
        HeaderInterceptor()
    }

    single {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(MoviesServiceApi::class.java) }

    single<MoviesService> {
        MoviesServiceImpl(get())
    }
}