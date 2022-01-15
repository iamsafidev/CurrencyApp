package com.iamsafi.digitifyTask.di

import com.iamsafi.digitifyTask.data.datastore.remote.currency.CurrencyRemoteDataStore
import com.iamsafi.digitifyTask.data.datastore.remote.currency.ICurrencyDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataStoreModule {

    @Binds
    @Singleton
    abstract fun provideCurrencyDataStore(dataStore: CurrencyRemoteDataStore): ICurrencyDataStore
}