package com.f97991.cryptocurrencies.api

import com.f97991.cryptocurrencies.data.CryptoBitcoinResponse
import com.f97991.cryptocurrencies.data.CryptoCardanoResponse
import com.f97991.cryptocurrencies.data.CryptoEthereumResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CryptoCurrencyApi {
    @GET("simple/price")
    suspend fun getCardanoPrice(
        @Query("ids")  ids: String,
        @Query("vs_currencies") currencies: String
    ): Response<CryptoCardanoResponse>

    @GET("simple/price")
    suspend fun getEthereum(
        @Query("ids")  ids: String,
        @Query("vs_currencies") currencies: String
    ): Response<CryptoEthereumResponse>

    @GET("simple/price")
    suspend fun getBitcoinPrice(
        @Query("ids")  ids: String,
        @Query("vs_currencies") currencies: String
    ): Response<CryptoBitcoinResponse>
}