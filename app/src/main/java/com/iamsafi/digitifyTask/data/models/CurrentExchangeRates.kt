package com.iamsafi.digitifyTask.data.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CurrentExchangeRates(
    @Json(name = "timestamp")
    var timestamp: Long = 0,

    @Json(name = "quotes")
    var quotes: HashMap<String, Double> = HashMap(),

    @Json(name = "source")
    var source: String? = ""
) : Parcelable