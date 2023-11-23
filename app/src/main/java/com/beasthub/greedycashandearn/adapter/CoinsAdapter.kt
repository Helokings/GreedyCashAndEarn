package com.beasthub.greedycashandearn.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beasthub.greedycashandearn.R
import com.beasthub.greedycashandearn.databinding.LayoutCoinsListBinding
import net.saawan.www.networkingpart.responsemodel.GreedyResponse

class CoinsAdapter(var context:Context, var coinList: List<GreedyResponse.CoinData>,var callback:DetectedAmountCallback) : RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {

//    var detectedAmountCallback:DetectedAmountCallback?=null
//    fun setdata(callback:DetectedAmountCallback)
//     {
//         this.detectedAmountCallback=callback
//     }

    private var selectedItem = -1

    interface DetectedAmountCallback
    {
        fun spentAmount(amount:String,position: Int)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : LayoutCoinsListBinding
        init {
            binding = LayoutCoinsListBinding.bind(itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_coins_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = coinList[position]
        holder.binding.coins.text = item.coin
        // Check if this item is the selected item
        val isSelected = position == selectedItem

        // Set the visibility of the tag view based on the selected state
        holder.binding.tag.visibility = if (isSelected) View.VISIBLE else View.INVISIBLE

        holder.binding.coinview.setOnClickListener {
            callback?.spentAmount(item.coin, position)

            // Update the selected item position
            selectedItem = position

            // Notify the adapter of the data change to update the UI
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return coinList.size
    }
}
