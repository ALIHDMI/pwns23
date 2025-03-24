package com.example.exchangeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.compose.ui.semantics.text
import androidx.compose.ui.tooling.data.position
import androidx.recyclerview.widget.RecyclerView
import kotlin.text.toLong

class ExchangeRateAdapter(context: Context, exchangeRates: List<ExchangeRate>?): BaseAdapter() {
    private val context: Context = context
    private val exchangeRates: List<ExchangeRate>? = exchangeRates
    override fun getCount(): Int {
        return exchangeRates!!.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listlayout, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        val currentItem = exchangeRates?.let { exchangeRates.get(position) }
        holder.nameTextView.text = currentItem?.let { currentItem.name }
        holder.streetTextView.text = currentItem?.let { currentItem.street }
        holder.filialsTextView.text = currentItem?.let { currentItem.filials_text }
        holder.usdInTextView.text = "USD покупка: ${currentItem?.let { currentItem.USD_in }}"
        holder.usdOutTextView.text = "USD продажа: ${currentItem?.let { currentItem.USD_out }}"
        holder.rubInTextView.text = "RUB покупка: ${currentItem?.let { currentItem.RUB_in}}"
        holder.rubOutTextView.text = "RUB продажа: ${currentItem?.let { currentItem.RUB_out}}"
        holder.eurInTextView.text = "EUR покупка: ${currentItem?.let { currentItem.EUR_in}}"
        holder.eurOutTextView.text = "EUR продажа: ${currentItem?.let { currentItem.EUR_out}}"

        return view!!
    }
    private class ViewHolder(view: View) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val streetTextView: TextView = view.findViewById(R.id.streetTextView)
        val filialsTextView: TextView = view.findViewById(R.id.filialsTextView)
        val usdInTextView: TextView = view.findViewById(R.id.usdInTextView)
        val usdOutTextView: TextView = view.findViewById(R.id.usdOutTextView)
        val rubInTextView: TextView = view.findViewById(R.id.rubInTextView)
        val rubOutTextView: TextView = view.findViewById(R.id.rubOutTextView)
        val eurInTextView: TextView = view.findViewById(R.id.eurInTextView)
        val eurOutTextView: TextView = view.findViewById(R.id.eurOutTextView)
    }
}