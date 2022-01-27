package com.iamsafi.currency.data.api.interceptor

import com.iamsafi.currency.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * To add the API Key on every request.
 */
class ApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url =
            original.url.newBuilder().addQueryParameter("access_key", BuildConfig.API_KEY).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}