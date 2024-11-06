package com.example.apptracking

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PedidosAdapter(private val pedidos: List<Pedido>) : RecyclerView.Adapter<PedidosAdapter.PedidoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pedido, parent, false)
        return PedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.tvPedidoNumero.text = pedido.numero
        holder.tvPedidoEstado.text = "Estado: ${pedido.estado}"

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PedidoActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = pedidos.size

    inner class PedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPedidoNumero: TextView = itemView.findViewById(R.id.tvPedidoNumero)
        val tvPedidoEstado: TextView = itemView.findViewById(R.id.tvPedidoEstado)
    }
}