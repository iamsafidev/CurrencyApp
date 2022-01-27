package com.iamsafi.currency.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iamsafi.currency.data.models.Currency

@Database(entities = [Currency::class], version = AppDatabase.VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRateDao(): RateDao

    companion object {
        const val VERSION = 1
        const val NAME = "app-db"
    }
}