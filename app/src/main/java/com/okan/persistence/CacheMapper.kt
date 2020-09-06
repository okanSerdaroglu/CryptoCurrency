package com.okan.persistence

import com.okan.model.main.Coin
import com.okan.utils.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<CoinCacheEntity, Coin> {

    override fun mapFromEntity(entity: CoinCacheEntity): Coin {
        return Coin(
            id = entity.id,
            symbol = entity.symbol,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: Coin): CoinCacheEntity {
        return CoinCacheEntity(
            id = domainModel.id,
            symbol = domainModel.symbol,
            name = domainModel.name
        )
    }

    fun mapFromEntityList(entities: List<CoinCacheEntity>): List<Coin> {
        return entities.map { coinCacheEntity ->
            mapFromEntity(coinCacheEntity)
        }
    }

}