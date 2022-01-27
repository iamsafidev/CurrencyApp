package com.iamsafi.currency.data.api.currency

import com.iamsafi.currency.data.models.CurrencyTypes
import com.iamsafi.currency.data.models.CurrentExchangeRates
import retrofit2.http.GET

interface CurrencyApi {
    @GET("live")
    suspend fun getCurrentExchangeRates(): CurrentExchangeRates

    @GET("list")
    suspend fun getCurrencyTypes(): CurrencyTypes
}