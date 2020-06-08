package de.abauer.giphy_clean_architecture.data.inject

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.abauer.giphy_androidcleanarchitecture.BuildConfig
import de.abauer.giphy_clean_architecture.data.repository.remote.SearchGiphysRemoteRepository
import de.abauer.giphy_clean_architecture.data.repository.remote.TrendingGiphysRemoteSource
import de.abauer.giphy_clean_architecture.data.repository.remote.mapper.GiphyRemoteMapper
import de.abauer.giphy_clean_architecture.data.service.ApiErrorHandler
import de.abauer.giphy_clean_architecture.data.service.ApiService
import de.abauer.giphy_clean_architecture.domain.repository.SearchGiphysRepository
import de.abauer.giphy_clean_architecture.domain.repository.TrendingGiphysRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val dataRemoteModule = module {
    single {
        createRetrofit(
            androidContext()
        )
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    single { ApiErrorHandler() }

    single { GiphyRemoteMapper() }

    single<TrendingGiphysRepository> {
        TrendingGiphysRemoteSource(
            apiKey = BuildConfig.API_KEY,
            apiService = get(),
            giphyRemoteMapper = get(),
            appDispatchers = get(),
            apiErrorHandler = get()
        )
    }

    single<SearchGiphysRepository> {
        SearchGiphysRemoteRepository(
            apiKey = BuildConfig.API_KEY,
            apiService = get(),
            giphyRemoteMapper = get(),
            appDispatchers = get(),
            apiErrorHandler = get()
        )
    }
}

fun createRetrofit(context: Context): Retrofit {
    return Retrofit.Builder()
        .client(createHttpClient(context))
        .baseUrl("https://api.giphy.com/")
        .addConverterFactory(MoshiConverterFactory.create(createMoshi()))
        .build()
}

fun createHttpClient(context: Context): OkHttpClient {
    val SERVICE_TIMEOUT_SECONDS = 60L
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClientBuilder = OkHttpClient.Builder()
        .connectTimeout(SERVICE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(SERVICE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addNetworkInterceptor(
            createHttpInspectorInterceptor(
                context
            )
        )

    return okHttpClientBuilder.build()
}

fun createHttpInspectorInterceptor(context: Context): Interceptor {
    val chuckerCollector = ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    return ChuckerInterceptor(
        context = context,
        collector = chuckerCollector,
        maxContentLength = 250000L
    )
}

fun createMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}


