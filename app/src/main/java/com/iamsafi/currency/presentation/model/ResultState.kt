package com.iamsafi.currency.presentation.model

import com.iamsafi.currency.data.datastore.remote.model.Failure

sealed class ResultState<out S, out E : Failure> {
    data class Success<out S>(val data: S? = null) : ResultState<S, Nothing>()
    data class Error<out E : Failure>(val failure: E) : ResultState<Nothing, E>()
}
