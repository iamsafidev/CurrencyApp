package com.iamsafi.digitifyTask.di

import com.iamsafi.digitifyTask.data.api.interceptor.ApiKeyInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor


@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun provideApiKeyInterceptor(interceptor: ApiKeyInterceptor): Interceptor

}