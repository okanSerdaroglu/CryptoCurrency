package com.okan.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNetworkEntity(

    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("symbol")
    @Expose
    val symbol: String,

    @SerializedName("name")
    @Expose
    val name: String

)

