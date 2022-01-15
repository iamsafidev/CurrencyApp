package com.iamsafi.digitifyTask.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamsafi.digitifyTask.data.datastore.remote.model.Either
import com.iamsafi.digitifyTask.data.datastore.remote.model.Failure
import com.iamsafi.digitifyTask.data.models.Currency
import com.iamsafi.digitifyTask.data.models.CurrencyTypes
import com.iamsafi.digitifyTask.data.models.CurrentExchangeRates
import com.iamsafi.digitifyTask.presentation.model.ResultState
import com.iamsafi.digitifyTask.repository.ICurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val currencyRepository: ICurrencyRepository,
) : ViewModel() {

    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility: LiveData<Boolean> = _progressVisibility

    private val _currencyData = MutableLiveData<ResultState<List<Currency>, Failure>>()
    val currencyData: LiveData<ResultState<List<Currency>, Failure>> = _currencyData

    val amount = MutableLiveData<String>()

    init {
        fetchCurrencyData()
    }

    private fun fetchCurrencyData() {
        viewModelScope.launch {
            _progressVisibility.value = true
            val exchangeRates = async { getCurrencyExchangeRates() }
            //Note: This delay is because on trial account parallel api call returns success false
            //TODO: Remove this delay when purchase account.
            delay(5000)
            val currencyList = async { getCurrenciesList() }
            when (val result = currencyRepository.getCurrenciesDetails(
                exchangeRates.await(), currencyList.await()
            )) {
                is Either.Success -> _currencyData.value = ResultState.Success(result.data)
                is Either.Error -> _currencyData.value = ResultState.Error(result.error)
            }
            _progressVisibility.value = false
        }
    }

    private suspend fun getCurrencyExchangeRates(): CurrentExchangeRates? {
        return when (val response = currencyRepository.getCurrentExchangeRates()) {
            is Either.Success -> response.data
            is Either.Error -> null
        }
    }

    private suspend fun getCurrenciesList(): CurrencyTypes? {
        return when (val response = currencyRepository.getCurrencyTypes()) {
            is Either.Success -> response.data
            is Either.Error -> null
        }
    }
}