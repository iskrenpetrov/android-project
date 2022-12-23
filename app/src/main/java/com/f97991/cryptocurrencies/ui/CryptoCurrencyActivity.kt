package com.f97991.cryptocurrencies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.f97991.cryptocurrencies.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CryptoCurrencyActivity() : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2
    lateinit var cryptoCurrencyAdapter: CryptoCurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto_currency)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager2 = findViewById(R.id.view_pager)
        cryptoCurrencyAdapter =  CryptoCurrencyAdapter(this)
        viewPager2.adapter = cryptoCurrencyAdapter

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }
        })

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = when(position) {
                0 -> "Bitcoin"
                1 -> "Ethereum"
                else -> "Cardano"
            }
        }.attach()
    }
}