package com.iamsafi.currency.di

import com.iamsafi.currency.repository.ICurrencyRepository
import com.iamsafi.currency.repository.impl.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideCurrencyRepository(repository: CurrencyRepository): ICurrencyRepository
}