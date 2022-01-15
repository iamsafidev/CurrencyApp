package com.iamsafi.digitifyTask.data.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CurrencyTypes(
    @Json(name = "currencies")
    var currencies: Map<String, String>,

    @Json(name = "success")
    var success: Boolean
) : Parcelable