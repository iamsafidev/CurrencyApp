package com.iamsafi.digitifyTask.data.datastore.remote.currency

import com.iamsafi.digitifyTask.data.datastore.remote.IRemoteDataStore
import com.iamsafi.digitifyTask.data.datastore.remote.model.Either
import com.iamsafi.digitifyTask.data.datastore.remote.model.Failure
import com.iamsafi.digitifyTask.data.models.CurrencyTypes
import com.iamsafi.digitifyTask.data.models.CurrentExchangeRates

interface ICurrencyDataStore : IRemoteDataStore {

    suspend fun getCurrentExchangeRates(): Either<CurrentExchangeRates, Failure>

    suspend fun getCurrencyTypes(): Either<CurrencyTypes, Failure>
}