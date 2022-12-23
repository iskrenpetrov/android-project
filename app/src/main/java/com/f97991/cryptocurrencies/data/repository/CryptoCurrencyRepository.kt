package com.f97991.cryptocurrencies.data.repository

import com.f97991.cryptocurrencies.api.CryptoCurrencyApi
import com.f97991.cryptocurrencies.data.CryptoBitcoinResponse
import com.f97991.cryptocurrencies.data.CryptoCardanoResponse
import com.f97991.cryptocurrencies.data.CryptoEthereumResponse
import com.f97991.cryptocurrencies.data.db.CryptoDAO
import com.f97991.cryptocurrencies.data.db.CryptoEntity
import java.util.*

class CryptoCurrencyRepository(
    private val cryptoCurrencyApi: CryptoCurrencyApi,
    private val cryptoDAO: CryptoDAO
    ) {

    suspend fun getBitcoinPrice(): CryptoBitcoinResponse {
        val response = cryptoCurrencyApi.getBitcoinPrice(
            "bitcoin",
            "usd"
        )
        val entity = CryptoEntity(
            Date(),
            response.body()?.bitcoin?.usd!!,
            "bitcoin"
        )
        cryptoDAO.insertAll(entity)
        return response.body()!!
    }

    suspend fun getEthereumPrice(): CryptoEthereumResponse {
        val response = cryptoCurrencyApi.getEthereum(
            "ethereum",
            "usd"
        )
        val entity = CryptoEntity(
            Date(),
            response.body()?.ethereum?.usd!!,
            "ethereum"
        )
        cryptoDAO.insertAll(entity)
        return response.body()!!
    }

    suspend fun getCardanoPrice(): CryptoCardanoResponse {
        val response = cryptoCurrencyApi.getCardanoPrice(
            "cardano",
            "usd"
        )
        val entity = CryptoEntity(
            Date(),
            response.body()?.cardano?.usd!!,
            "cardano"
        )
        cryptoDAO.insertAll(entity)
        return response.body()!!
    }

    suspend fun getDbCryptoData(cryptoName: String): List<CryptoEntity> {
        return cryptoDAO.getAll(cryptoName)
    }
}