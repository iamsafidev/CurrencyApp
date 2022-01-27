package com.iamsafi.currency.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iamsafi.currency.data.models.Currency

@Dao
interface RateDao {

    @Query("SELECT * from currencyTable")
    suspend fun getAllList(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAllRate(rateList: List<Currency>)
}