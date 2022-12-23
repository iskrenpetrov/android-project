package com.f97991.cryptocurrencies.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CryptoDAO {
    @Query("SELECT * FROM crypto WHERE crypto.crypto_name = :cryptoName")
    suspend fun getAll(cryptoName: String): List<CryptoEntity>

    @Insert
    suspend fun insertAll(vararg users: CryptoEntity)
}