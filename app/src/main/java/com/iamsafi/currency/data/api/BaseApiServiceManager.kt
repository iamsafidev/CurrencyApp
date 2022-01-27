package com.iamsafi.currency.data.api

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.iamsafi.currency.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Base class which encapsulates logic for creating API services
 */
private const val DEFAULT_NETWORK_TIMEOUT = 20L

abstract class BaseApiServiceManager(
    private val authInterceptor: Interceptor,
    private val context: Context,
    private val networkInterceptor: Interceptor
) {

    protected fun <S> createService(serviceClass: Class<S>): S {
        val moshi = Moshi.Builder().build()
        return Retrofit.Builder()
            .client(buildHttpClient())
            .baseUrl(getBaseUrl())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(serviceClass)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun buildHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(getCacheSize(context))
            .addNetworkInterceptor(networkInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(DEFAULT_NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_NETWORK_TIMEOUT, TimeUnit.SECONDS)

        return builder.build()
    }

    private fun getCacheSize(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    private fun getBaseUrl() = BuildConfig.BASE_URL
}