package com.f97991.cryptocurrencies.ui

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CryptoCurrencyAdapter(@NonNull fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BitcoinFragment()
            1 -> EthereumFragment()
            2 -> CardanoFragment()
            else -> BitcoinFragment()
        }
        // Return a NEW fragment instance in createFragment(int)
    }
}