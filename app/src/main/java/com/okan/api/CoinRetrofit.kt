package com.okan.api

import retrofit2.http.GET

interface CoinRetrofit {

    @GET("")
    suspend fun getCoinList(): List<CoinNetworkEntity>

}