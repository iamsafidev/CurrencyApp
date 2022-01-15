package com.iamsafi.digitifyTask.data.api

import androidx.annotation.VisibleForTesting
import com.iamsafi.digitifyTask.BuildConfig
import com.squareup.moshi.Moshi
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
            .addInterceptor(authInterceptor)
            .connectTimeout(DEFAULT_NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_NETWORK_TIMEOUT, TimeUnit.SECONDS)

        return builder.build()
    }

    private fun getBaseUrl() = BuildConfig.BASE_URL
}