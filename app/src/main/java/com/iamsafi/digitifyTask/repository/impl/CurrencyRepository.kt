package com.iamsafi.digitifyTask.repository.impl

import com.iamsafi.digitifyTask.data.datastore.remote.currency.ICurrencyDataStore
import com.iamsafi.digitifyTask.data.datastore.remote.model.Either
import com.iamsafi.digitifyTask.data.datastore.remote.model.Failure
import com.iamsafi.digitifyTask.data.models.Currency
import com.iamsafi.digitifyTask.data.models.CurrencyTypes
import com.iamsafi.digitifyTask.data.models.CurrentExchangeRates
import com.iamsafi.digitifyTask.presentation.utils.safeLet
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

    override fun getCurrenciesDetails(
        exchangeRatesList: CurrentExchangeRates?,
        currencyTypeList: CurrencyTypes?
    ): Either<List<Currency>, Failure> {
        return safeLet(
            exchangeRatesList,
            currencyTypeList
        ) { exchangeRates, currencyType ->
            if (exchangeRates.success && currencyType.success) {
                val list = ArrayList<Currency>()
                for (entry in currencyType.currencies) {
                    val currency = Currency()
                    currency.code = entry.key
                    currency.name = entry.value
                    currency.rate =
                        exchangeRates.quotes[exchangeRates.source + currency.code]!!
                    list.add(currency)
                }
                Either.Success(list)
            } else
                Either.Error(Failure())
        } ?: Either.Error(Failure())

    }
}