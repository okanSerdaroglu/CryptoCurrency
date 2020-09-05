package com.okan.api

import com.okan.model.Coin
import com.okan.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<CoinNetworkEntity, Coin> {

    override fun mapFromEntity(entity: CoinNetworkEntity): Coin {
        return Coin(
            id = entity.id,
            symbol = entity.symbol,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: Coin): CoinNetworkEntity {
        return CoinNetworkEntity(
            id = domainModel.id,
            symbol = domainModel.symbol,
            name = domainModel.name
        )
    }

    fun mapFromEntityList(entities: List<CoinNetworkEntity>): List<Coin> {
        return entities.map { coinNetworkEntity ->
            mapFromEntity(coinNetworkEntity)
        }
    }

}