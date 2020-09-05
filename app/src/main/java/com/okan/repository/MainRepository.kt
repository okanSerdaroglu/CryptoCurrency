package com.okan.repository

import com.okan.api.CoinRetrofit
import com.okan.api.NetworkMapper
import com.okan.model.Coin
import com.okan.persistence.CacheMapper
import com.okan.persistence.CoinDao
import com.okan.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    private val coinDao: CoinDao,
    private val coinRetrofit: CoinRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getCoinList(): Flow<DataState<List<Coin>>> = flow {
        emit(DataState.Loading)
        delay(1500)
        try {
            // retrieve from network
            val networkCoinList = coinRetrofit.getCoinList()
            val coinList = networkMapper.mapFromEntityList(networkCoinList)

            // send to cache
            for (coin in coinList) {
                coinDao.insert(cacheMapper.mapToEntity(coin))
            }

            // read from the cache
            val cachedCoinList = coinDao.getCoinList()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedCoinList)))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}