package com.iamsafi.digitifyTask.data.datastore.remote.model

sealed class Either<out S, out E : Failure> {
    data class Success<out S>(val data: S) : Either<S, Nothing>()
    data class Error<out E : Failure>(val error: E) : Either<Nothing, E>()
}