package com.f97991.cryptocurrencies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.f97991.cryptocurrencies.R
import com.f97991.cryptocurrencies.data.db.CryptoEntity
import java.util.*

class BitcoinFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val adapter = CryptoCurrencyListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bitcoin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_crypto_list)
        val viewModel by viewModels<CryptoListViewModel>()
        recyclerView.adapter = adapter
        viewModel.fetchCryptoList("bitcoin")

        viewModel.cryptoList.observe(viewLifecycleOwner) {
            adapter.addList(it)
        }
    }
}