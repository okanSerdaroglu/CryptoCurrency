package com.okan.api

import retrofit2.http.GET

interface CoinRetrofit {

    @GET("coins/list")
    suspend fun getCoinList(): List<CoinNetworkEntity>

}