package com.iamsafi.currency.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "currencyTable")
data class Currency(

    @PrimaryKey
    @ColumnInfo(name = "code")
    var code: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "rate")
    var rate: Double = 1.0,

    @Ignore
    var amount: String = "",

    @Ignore
    var countryFlag: Int = 0
)