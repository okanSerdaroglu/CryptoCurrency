package com.okan.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coinCacheEntity: CoinCacheEntity): Long

    @Query("SELECT * FROM coins")
    suspend fun getCoinList(): List<CoinCacheEntity>

}