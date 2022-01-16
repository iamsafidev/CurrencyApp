package com.iamsafi.digitifyTask.repository

import com.iamsafi.digitifyTask.data.datastore.remote.model.Either
import com.iamsafi.digitifyTask.data.datastore.remote.model.Failure
import com.iamsafi.digitifyTask.data.models.Currency
import com.iamsafi.digitifyTask.data.models.CurrencyTypes
import com.iamsafi.digitifyTask.data.models.CurrentExchangeRates

interface ICurrencyRepository {

    suspend fun getCurrentExchangeRates(): Either<CurrentExchangeRates, Failure>

    suspend fun getCurrencyTypes(): Either<CurrencyTypes, Failure>

    suspend fun getCurrenciesDetails(
        exchangeRatesList: CurrentExchangeRates?,
        currencyTypeList: CurrencyTypes?
    ): Either<List<Currency>, Failure>

    suspend fun updateAllExchangeRates(saveList: List<Currency>)

    suspend fun getCurrenciesList(): Either<List<Currency>, Failure>
}