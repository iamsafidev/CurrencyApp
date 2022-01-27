package com.iamsafi.currency.data.datastore.remote.currency

import com.iamsafi.currency.data.api.currency.CurrencyApiServiceManager
import com.iamsafi.currency.data.datastore.remote.model.ApiRequestError
import com.iamsafi.currency.data.datastore.remote.model.Either
import com.iamsafi.currency.data.datastore.remote.model.Failure
import com.iamsafi.currency.data.models.CurrencyTypes
import com.iamsafi.currency.data.models.CurrentExchangeRates
import javax.inject.Inject

class CurrencyRemoteDataStore @Inject constructor(private val apiManager: CurrencyApiServiceManager) :
    ICurrencyDataStore {

    override suspend fun getCurrentExchangeRates(): Either<CurrentExchangeRates, Failure> {
        return executeRetrofitCall { apiManager.currencyApi.getCurrentExchangeRates() }
    }

    override suspend fun getCurrencyTypes(): Either<CurrencyTypes, Failure> {
        return executeRetrofitCall { apiManager.currencyApi.getCurrencyTypes() }
    }

    override fun mapToFailure(apiRequestError: ApiRequestError): Failure {
        return Failure()
    }
}