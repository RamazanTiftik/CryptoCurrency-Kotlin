package com.example.cryptocoinretrofitkotlin.adapter

import android.content.Intent
import android.graphics.Color
import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoinretrofitkotlin.R
import com.example.cryptocoinretrofitkotlin.databinding.RecyclerRowBinding
import com.example.cryptocoinretrofitkotlin.model.CryptoModel

class CryptoAdapter(private val cryptoList: ArrayList<CryptoModel>): RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    private val colors: Array<String> = arrayOf("#ab9fd4","#ffa500","#ffdb1a","#ff0000","#680cab","#3399ff","#3ea958","#1a0dab")

    class CryptoHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.binding.currencyText.setText(cryptoList.get(position).currency)
        holder.binding.priceText.setText(cryptoList.get(position).price)
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position%8]))

    }

}