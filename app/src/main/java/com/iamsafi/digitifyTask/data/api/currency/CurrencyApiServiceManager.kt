package com.iamsafi.digitifyTask.data.api.currency

import com.iamsafi.digitifyTask.data.api.BaseApiServiceManager
import okhttp3.Interceptor
import javax.inject.Inject

/**
 * Class which creates Currency API service for calling Currency endpoints
 */
class CurrencyApiServiceManager @Inject constructor(
    authInterceptor: Interceptor,
) : BaseApiServiceManager(authInterceptor) {

    val currencyApi: CurrencyApi by lazy { createService(CurrencyApi::class.java) }
}
