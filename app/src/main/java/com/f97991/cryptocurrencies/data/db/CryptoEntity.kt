package com.f97991.cryptocurrencies.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "crypto"
)
data class CryptoEntity(
    @ColumnInfo(name = "fetch_date") val fetchDate: Date,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "crypto_name") val cryptoName: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)