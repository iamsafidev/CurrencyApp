package com.iamsafi.currency.data.api.currency

import android.content.Context
import com.iamsafi.currency.data.api.BaseApiServiceManager
import com.iamsafi.currency.di.CacheDataInterceptor
import com.iamsafi.currency.di.KeyInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import javax.inject.Inject

/**
 * Class which creates Currency API service for calling Currency endpoints
 */
class CurrencyApiServiceManager @Inject constructor(
    @KeyInterceptor authInterceptor: Interceptor,
    @ApplicationContext context: Context,
    @CacheDataInterceptor networkInterceptor: Interceptor
) : BaseApiServiceManager(authInterceptor, context, networkInterceptor) {

    val currencyApi: CurrencyApi by lazy { createService(CurrencyApi::class.java) }
}
