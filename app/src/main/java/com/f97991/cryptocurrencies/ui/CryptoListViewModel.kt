package com.f97991.cryptocurrencies.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.f97991.cryptocurrencies.data.db.CryptoEntity
import com.f97991.cryptocurrencies.di.ApiModule
import com.f97991.cryptocurrencies.di.AppModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CryptoListViewModel(application: Application) : AndroidViewModel(application) {
    private val cryptoCurrencyApi = ApiModule.getApi()
    private val cryptoDAO = AppModule.getDao(application)
    private val cryptoCurrencyRepository = AppModule.getRepository(
        cryptoCurrencyApi,
        cryptoDAO
    )

    val cryptoList = MutableLiveData<List<CryptoEntity>>()

    fun fetchCryptoList(cryptoName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = cryptoCurrencyRepository.getDbCryptoData(cryptoName)
                withContext(Dispatchers.Main) {
                    cryptoList.value = response.sortedByDescending {
                        it.fetchDate
                    }
                }
            } catch (err: Throwable) {
                println(err)
            }
        }
    }
}