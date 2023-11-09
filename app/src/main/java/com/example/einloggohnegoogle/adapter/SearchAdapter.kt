package com.example.einloggohnegoogle.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.databinding.ListItemResultBinding

class SearchAdapter(private val dataset: List<Rezept>) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListItemResultBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rezept = dataset[position]

        holder.binding.rezeptNameTV2.text = rezept.name
        holder.binding.zutatenTV2.text = rezept.zutaten
        holder.binding.zubereitungTV2.text = rezept.zubereitung
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
