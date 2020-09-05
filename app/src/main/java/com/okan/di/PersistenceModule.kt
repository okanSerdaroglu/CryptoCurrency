package com.okan.di

import android.content.Context
import androidx.room.Room
import com.okan.persistence.CoinDao
import com.okan.persistence.CoinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object PersistenceModule {

    @Singleton
    @Provides
    fun provideCoinDb(@ApplicationContext context: Context): CoinDatabase {
        return Room.databaseBuilder(
            context,
            CoinDatabase::class.java,
            CoinDatabase.DATABASE_NAME,
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCoinDAO(coinDatabase: CoinDatabase): CoinDao {
        return coinDatabase.coinDao()
    }

}