package com.beasthub.greedycashandearn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beasthub.greedycashandearn.Item
import com.beasthub.greedycashandearn.R

class ItemAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val rankTextView: TextView = itemView.findViewById(R.id.ranking)
        val coinsTextView: TextView = itemView.findViewById(R.id.coins)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.threewinnerslist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.nameTextView.text = "Name: ${item.name}"
        holder.coinsTextView.text = "Total Coins: ${item.coins.toString()}"
        holder.rankTextView.text = "Rank: ${item.rank}"
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
