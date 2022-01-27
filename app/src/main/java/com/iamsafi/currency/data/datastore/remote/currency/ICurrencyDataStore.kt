package com.iamsafi.currency.data.datastore.remote.currency

import com.iamsafi.currency.data.datastore.remote.IRemoteDataStore
import com.iamsafi.currency.data.datastore.remote.model.Either
import com.iamsafi.currency.data.datastore.remote.model.Failure
import com.iamsafi.currency.data.models.CurrencyTypes
import com.iamsafi.currency.data.models.CurrentExchangeRates

interface ICurrencyDataStore : IRemoteDataStore {

    suspend fun getCurrentExchangeRates(): Either<CurrentExchangeRates, Failure>

    suspend fun getCurrencyTypes(): Either<CurrencyTypes, Failure>
}