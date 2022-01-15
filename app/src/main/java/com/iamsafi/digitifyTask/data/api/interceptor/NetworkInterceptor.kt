package com.iamsafi.digitifyTask.data.api.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NetworkInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxAge(30, TimeUnit.MINUTES)
            .build()

        return response.newBuilder()
            .removeHeader(HEADER_PRAGMA)
            .removeHeader(HEADER_CACHE_CONTROL)
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }

    companion object {
        private const val HEADER_CACHE_CONTROL = "Cache-Control"
        private const val HEADER_PRAGMA = "Pragma"
    }
}