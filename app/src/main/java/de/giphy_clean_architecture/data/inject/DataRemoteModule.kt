package de.giphy_clean_architecture.data.inject

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import de.giphy_androidcleanarchitecture.BuildConfig
import de.giphy_clean_architecture.data.repository.remote.SearchGiphysRemoteRepository
import de.giphy_clean_architecture.data.repository.remote.TrendingGiphyRemoteSource
import de.giphy_clean_architecture.data.repository.remote.mapper.TrendingGiphyRemoteMapper
import de.giphy_clean_architecture.data.service.ApiErrorHandler
import de.giphy_clean_architecture.data.service.ApiService
import de.giphy_clean_architecture.domain.repository.SearchGiphyRepository
import de.giphy_clean_architecture.domain.repository.TrendingGiphyRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val dataRemoteModule = module {
    single {
        createRetrofit(
            androidContext()
        )
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    single { ApiErrorHandler() }

    single { TrendingGiphyRemoteMapper() }

    single<TrendingGiphyRepository> {
        TrendingGiphyRemoteSource(
            apiKey = BuildConfig.API_KEY,
            apiService = get(),
            trendingGiphyRemoteMapper = get(),
            appDispatchers = get(),
            apiErrorHandler = get()
        )
    }

    single<SearchGiphyRepository> {
        SearchGiphysRemoteRepository(
            apiKey = BuildConfig.API_KEY,
            apiService = get(),
            trendingGiphyRemoteMapper = get(),
            appDispatchers = get(),
            apiErrorHandler = get()
        )
    }
}

fun createRetrofit(context: Context): Retrofit {
    return Retrofit.Builder()
        .client(createHttpClient(context))
        .baseUrl("https://api.giphy.com/")
        .addConverterFactory(GsonConverterFactory.create())
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


