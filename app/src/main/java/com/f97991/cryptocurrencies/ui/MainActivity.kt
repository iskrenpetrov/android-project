package com.f97991.cryptocurrencies.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.f97991.cryptocurrencies.R
import java.util.*


class MainActivity : AppCompatActivity(),  AdapterView.OnItemSelectedListener {
    lateinit var tvCryptoInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel by viewModels<CryptoViewModel>()

        tvCryptoInfo = findViewById(R.id.tv_crypto_info)
        val fetchBtn = findViewById<Button>(R.id.btn_fetch)
        val goToNextScreen = findViewById<Button>(R.id.btn_go_to_next_page)
        val spinner = findViewById<Spinner>(R.id.crypto_spinner)
        goToNextScreen.setOnClickListener {
            val myIntent = Intent(this, CryptoCurrencyActivity::class.java)
            startActivity(myIntent)
        }

        fetchBtn.setOnClickListener {
            viewModel.fetchCryptoByName(spinner.selectedItem.toString()
                .lowercase(Locale.getDefault()))
        }

        viewModel.cryptoBitcoin.observe(this, Observer {
            tvCryptoInfo.visibility = View.VISIBLE
            tvCryptoInfo.text = getInfoText(
                "Bitcoin", it.bitcoin.usd
            )
        })
        viewModel.cryptoCardano.observe(this, Observer {
            tvCryptoInfo.visibility = View.VISIBLE
            tvCryptoInfo.text = getInfoText(
                "Cardano", it.cardano.usd
            )
        })
        viewModel.cryptoEthereum.observe(this, Observer {
            tvCryptoInfo.visibility = View.VISIBLE
            tvCryptoInfo.text = getInfoText(
                "Ethereum", it.ethereum.usd
            )
        })

        ArrayAdapter.createFromResource(
            this,
            R.array.crypto_currencies,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
//        tvCryptoInfo.text = getInfoText(position)
        tvCryptoInfo.visibility = View.GONE
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun getInfoText(cryptoCurrency: String, price: Double) =
        getString(R.string.info_crypto, cryptoCurrency, price)
}