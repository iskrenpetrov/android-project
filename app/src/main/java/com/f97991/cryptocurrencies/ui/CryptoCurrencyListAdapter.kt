package com.f97991.cryptocurrencies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.f97991.cryptocurrencies.R
import com.f97991.cryptocurrencies.data.db.CryptoEntity

class CryptoCurrencyListAdapter(
    private var list: List<CryptoEntity> = listOf()
) : RecyclerView.Adapter<CryptoCurrencyListAdapter.ViewHolder>() {

    fun addList(list: List<CryptoEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView
        val date: TextView

        init {
            // Define click listener for the ViewHolder's View
            price = view.findViewById(R.id.tv_price)
            date = view.findViewById(R.id.tv_date)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_crypto, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.price.text = list[position].price.toString()
        viewHolder.date.text = list[position].fetchDate.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = list.size

}