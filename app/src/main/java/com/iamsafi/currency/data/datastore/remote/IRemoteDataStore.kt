package com.iamsafi.currency.data.datastore.remote

import com.iamsafi.currency.data.datastore.remote.model.*
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.net.SocketTimeoutException

interface IRemoteDataStore {
    suspend fun <T> executeRetrofitCall(
        retrofitCall: suspend () -> T
    ): Either<T, Failure> {
        return try {
            val callResult = retrofitCall()
            Either.Success(callResult)
        } catch (exception: Exception) {
            val failure = fromException(exception)
            Either.Error(failure)
        }
    }

    fun mapToFailure(apiRequestError: ApiRequestError): Failure

    fun fromException(exception: Exception): Failure {
        if (exception is SocketTimeoutException) {
            return NetworkFailure()
        } else {
            val apiRequestError = if (exception is HttpException) {
                try {
                    val errorMessage = exception.response()?.errorBody()?.string()

                    val serverError = if (errorMessage.isNullOrEmpty()) {
                        ServerError(null, null)
                    } else {
                        val jsonAdapter = Moshi.Builder().build().adapter(ServerError::class.java)
                        jsonAdapter.fromJson(errorMessage)!!
                    }

                    serverError.error?.code = exception.response()?.code()
                    serverError
                } catch (e: Exception) {
                    e.printStackTrace()
                    UnknownRequestError()
                }
            } else {
                UnknownRequestError()
            }

            return mapToFailure(apiRequestError)
        }
    }
}