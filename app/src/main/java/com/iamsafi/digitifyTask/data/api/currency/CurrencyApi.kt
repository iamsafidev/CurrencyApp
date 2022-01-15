package com.iamsafi.digitifyTask.data.api.currency

import com.iamsafi.digitifyTask.data.models.CurrencyTypes
import com.iamsafi.digitifyTask.data.models.CurrentExchangeRates
import retrofit2.http.GET

interface CurrencyApi {
    @GET("live")
    suspend fun getCurrentExchangeRates(): CurrentExchangeRates

    @GET("list")
    suspend fun getCurrencyTypes(): CurrencyTypes
}