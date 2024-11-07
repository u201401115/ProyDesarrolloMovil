package com.example.apptracking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RutaLogAdapter(private val transactionList: List<RutaLog>) : RecyclerView.Adapter<RutaLogAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ruta_log, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.tollNameText.text = transaction.tollName
        holder.licensePlateText.text = transaction.licensePlate
        holder.dateText.text = transaction.date
        holder.priceText.text = transaction.price
    }

    override fun getItemCount(): Int = transactionList.size

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tollNameText: TextView = itemView.findViewById(R.id.tollNameText)
        val licensePlateText: TextView = itemView.findViewById(R.id.licensePlateText)
        val dateText: TextView = itemView.findViewById(R.id.dateText)
        val priceText: TextView = itemView.findViewById(R.id.priceText)
    }
}