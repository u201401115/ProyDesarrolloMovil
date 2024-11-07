package com.example.apptracking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AssetAdapter(private val assetList: List<Pasivo>) : RecyclerView.Adapter<AssetAdapter.AssetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pasivo, parent, false)
        return AssetViewHolder(view)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val asset = assetList[position]
        holder.tvAssetId.text = "Asset ID: ${asset.assetId}"
        holder.tvAssetName.text = "Asset Name: ${asset.assetName}"
        holder.tvConsignee.text = "Consignee: ${asset.consignee}"
    }

    override fun getItemCount(): Int = assetList.size

    class AssetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAssetId: TextView = itemView.findViewById(R.id.tvAssetId)
        val tvAssetName: TextView = itemView.findViewById(R.id.tvAssetName)
        val tvConsignee: TextView = itemView.findViewById(R.id.tvConsignee)
    }
}