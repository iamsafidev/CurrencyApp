package com.iamsafi.digitifyTask.repository.impl

import com.iamsafi.digitifyTask.data.datastore.remote.currency.ICurrencyDataStore
import com.iamsafi.digitifyTask.data.datastore.remote.model.Either
import com.iamsafi.digitifyTask.data.datastore.remote.model.Failure
import com.iamsafi.digitifyTask.data.models.CurrencyTypes
import com.iamsafi.digitifyTask.data.models.CurrentExchangeRates
import com.iamsafi.digitifyTask.repository.ICurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val currencyDataStore: ICurrencyDataStore) :
    ICurrencyRepository {

    override suspend fun getCurrentExchangeRates(): Either<CurrentExchangeRates, Failure> {
        return withContext(Dispatchers.IO) {
            currencyDataStore.getCurrentExchangeRates()
        }
    }

    override suspend fun getCurrencyTypes(): Either<CurrencyTypes, Failure> {
        return withContext(Dispatchers.IO) {
            currencyDataStore.getCurrencyTypes()
        }
    }
}