package com.iamsafi.digitifyTask.di

import android.content.Context
import androidx.room.Room
import com.iamsafi.digitifyTask.data.room.AppDatabase
import com.iamsafi.digitifyTask.data.room.RateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }

    @Provides
    fun providesRateDao(appDatabase: AppDatabase): RateDao {
        return appDatabase.getRateDao()
    }
}