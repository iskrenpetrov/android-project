package com.f97991.cryptocurrencies.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.f97991.cryptocurrencies.data.CryptoBitcoinResponse
import com.f97991.cryptocurrencies.data.CryptoCardanoResponse
import com.f97991.cryptocurrencies.data.CryptoEthereumResponse
import com.f97991.cryptocurrencies.di.ApiModule
import com.f97991.cryptocurrencies.di.AppModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CryptoViewModel(application: Application) : AndroidViewModel(application) {
    val cryptoCurrencyApi = ApiModule.getApi()
    val cryptoDAO = AppModule.getDao(application)

    val cryptoCurrencyRepository = AppModule.getRepository(
        cryptoCurrencyApi,
        cryptoDAO
    )

    val cryptoBitcoin = MutableLiveData<CryptoBitcoinResponse>()
    val cryptoCardano = MutableLiveData<CryptoCardanoResponse>()
    val cryptoEthereum = MutableLiveData<CryptoEthereumResponse>()

    fun fetchCryptoByName(cryptoName: String) {
        when (cryptoName) {
            "bitcoin" -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = cryptoCurrencyRepository.getBitcoinPrice()
                        withContext(Dispatchers.Main) {
                            cryptoBitcoin.value = response
                        }
                    } catch (err: Throwable) {
                        println(err)
                    }
                }
            }
            "cardano" -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = cryptoCurrencyRepository.getCardanoPrice()

                        withContext(Dispatchers.Main) {
                            cryptoCardano.value = response
                        }
                    } catch (err: Throwable) {
                        println("Error ${err.message}")
                    }
                }
            }
            else -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = cryptoCurrencyRepository.getEthereumPrice()

                        withContext(Dispatchers.Main) {
                            cryptoEthereum.value = response
                        }
                    } catch (exception: Throwable) {
                        println("err")
                    }
                }
            }
        }
    }
}