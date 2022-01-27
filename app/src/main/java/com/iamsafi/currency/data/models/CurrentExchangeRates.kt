package com.iamsafi.currency.data.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CurrentExchangeRates(
    @Json(name = "timestamp")
    var timestamp: Long,

    @Json(name = "quotes")
    var quotes: Map<String, Double>,

    @Json(name = "source")
    var source: String,

    @Json(name = "success")
    var success: Boolean
) : Parcelable