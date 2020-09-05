package com.okan.di

import com.okan.api.CoinRetrofit
import com.okan.api.NetworkMapper
import com.okan.persistence.CacheMapper
import com.okan.persistence.CoinDao
import com.okan.repository.main.CoinListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        coinDao: CoinDao,
        retrofit: CoinRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): CoinListRepository {
        return CoinListRepository(
            coinDao = coinDao,
            coinRetrofit = retrofit,
            cacheMapper = cacheMapper,
            networkMapper = networkMapper
        )
    }

}