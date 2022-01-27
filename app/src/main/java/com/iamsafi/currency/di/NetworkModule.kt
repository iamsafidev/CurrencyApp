package com.iamsafi.currency.di

import com.iamsafi.currency.data.api.interceptor.ApiKeyInterceptor
import com.iamsafi.currency.data.api.interceptor.NetworkInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KeyInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CacheDataInterceptor

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    @KeyInterceptor
    @Binds
    abstract fun provideApiKeyInterceptor(interceptor: ApiKeyInterceptor): Interceptor

    @CacheDataInterceptor
    @Binds
    abstract fun provideCacheDataInterceptor(interceptor: NetworkInterceptor): Interceptor
}