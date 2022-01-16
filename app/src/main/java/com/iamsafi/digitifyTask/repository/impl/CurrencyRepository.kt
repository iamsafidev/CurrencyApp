package com.iamsafi.digitifyTask.repository.impl

import com.iamsafi.digitifyTask.data.datastore.remote.currency.ICurrencyDataStore
import com.iamsafi.digitifyTask.data.datastore.remote.model.Either
import com.iamsafi.digitifyTask.data.datastore.remote.model.Failure
import com.iamsafi.digitifyTask.data.models.Currency
import com.iamsafi.digitifyTask.data.models.CurrencyTypes
import com.iamsafi.digitifyTask.data.models.CurrentExchangeRates
import com.iamsafi.digitifyTask.data.room.RateDao
import com.iamsafi.digitifyTask.presentation.utils.safeLet
import com.iamsafi.digitifyTask.repository.ICurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyDataStore: ICurrencyDataStore,
    private val currencyListDao: RateDao
) : ICurrencyRepository {

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

    override suspend fun getCurrenciesDetails(
        exchangeRatesList: CurrentExchangeRates?,
        currencyTypeList: CurrencyTypes?
    ): Either<List<Currency>, Failure> {
        return withContext(Dispatchers.IO){
            safeLet(
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

    override suspend fun updateAllExchangeRates(saveList: List<Currency>) {
        withContext(Dispatchers.IO) {
            currencyListDao.updateAllRate(saveList)
        }
    }

    override suspend fun getCurrenciesList(): Either<List<Currency>, Failure> {
        var currencyList: List<Currency>
        withContext(Dispatchers.IO) {
            currencyList = currencyListDao.getAllList()
        }
        return if (currencyList.isNotEmpty())
            Either.Success(currencyList)
        else
            Either.Error(Failure())
    }
}