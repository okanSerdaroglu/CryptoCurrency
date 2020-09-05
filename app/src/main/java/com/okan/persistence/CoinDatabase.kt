package com.okan.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CoinCacheEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao

    companion object {
        const val DATABASE_NAME: String = "coin_db"
    }

}