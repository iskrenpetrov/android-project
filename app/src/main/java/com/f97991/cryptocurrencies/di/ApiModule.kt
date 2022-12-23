package com.f97991.cryptocurrencies.di

import com.f97991.cryptocurrencies.api.CryptoCurrencyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiModule {
    fun getApi(): CryptoCurrencyApi{
        return getRetrofitClient().create(CryptoCurrencyApi::class.java);
    }

    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}