package com.f97991.cryptocurrencies.di

import android.app.Application
import androidx.room.Room
import com.f97991.cryptocurrencies.api.CryptoCurrencyApi
import com.f97991.cryptocurrencies.data.db.AppDatabase
import com.f97991.cryptocurrencies.data.db.CryptoDAO
import com.f97991.cryptocurrencies.data.repository.CryptoCurrencyRepository

object AppModule {

    fun getRepository(
        api: CryptoCurrencyApi,
        cryptoDAO: CryptoDAO): CryptoCurrencyRepository {
        return CryptoCurrencyRepository(api, cryptoDAO);
    }

    fun getDb(applicationContext: Application): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    fun getDao(applicationContext: Application): CryptoDAO{
        return getDb(applicationContext).cryptoDao()
    }
}