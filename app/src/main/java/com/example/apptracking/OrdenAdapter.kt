package com.example.apptracking
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(private val orderList: List<Orden>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_orden, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.dateText.text = "Fecha : ${order.date}"
        holder.assetIdText.text = "Orden ID : ${order.assetId}"
        holder.consigneeText.text = "Consignatario : ${order.consignee}"
        holder.blNumberText.text = "Producto ID : ${order.blNumber}"
    }

    override fun getItemCount(): Int = orderList.size

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateText: TextView = itemView.findViewById(R.id.dateText)
        val assetIdText: TextView = itemView.findViewById(R.id.assetIdText)
        val consigneeText: TextView = itemView.findViewById(R.id.consigneeText)
        val blNumberText: TextView = itemView.findViewById(R.id.blNumberText)
        val viewDetailText: TextView = itemView.findViewById(R.id.viewDetailText)
    }
}