package com.iamsafi.currency.data.datastore.remote.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

abstract class ApiRequestError

class UnknownRequestError : ApiRequestError()

@Parcelize
@JsonClass(generateAdapter = true)
data class ServerError(
    @Json(name = "error")
    val error: Error?,
    @Json(name = "success")
    val success: Boolean?
) : ApiRequestError(), Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Error(
    @Json(name = "code")
    var code: Int?,
    @Json(name = "info")
    val info: String
) : Parcelable
