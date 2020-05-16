package de.giphy_clean_architecture.data

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import de.giphy_clean_architecture.data.repository.remote.GiphyTrendingRemoteSource
import de.giphy_clean_architecture.data.service.ApiService
import de.giphy_clean_architecture.domain.repository.GiphyTrendingRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val dataRemoteModule = module {
    single { createRetrofit(androidContext()) }

    single { get<Retrofit>().create(ApiService::class.java) }

    single<GiphyTrendingRepository> {
        GiphyTrendingRemoteSource(
            apiKey = "enter_api_key",
            apiService = get()
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
        .addNetworkInterceptor(StethoInterceptor())
        .addInterceptor(interceptor)
        .addNetworkInterceptor(createHttpInspectorInterceptor(context))

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


