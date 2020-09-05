package com.okan.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "symbol")
    var symbol: String,

    @ColumnInfo(name = "name")
    var name: String
)