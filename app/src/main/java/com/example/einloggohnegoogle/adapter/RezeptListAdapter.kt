package com.example.einloggohnegoogle.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.databinding.ItemRezeptBinding
import com.example.einloggohnegoogle.ui.DataFragmentDirections
import com.example.einloggohnegoogle.ui.KlausFragmentDirections


class RezeptListAdapter(private val rezepte: List<Rezept>, private val itemClickListener: (Rezept) -> Unit) : RecyclerView.Adapter<RezeptListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRezeptBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRezeptBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rezepte.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rezept = rezepte[position]
        holder.binding.rezeptNameTV.text = "Name: ${rezept.name}"
        holder.binding.zutatenTV.text = "Zutaten: ${rezept.zutaten}"
        holder.binding.zubereitungTV.text = "Zubereitung: ${rezept.zubereitung}"
        holder.binding.erstellerTV.text="Ersteller: ${rezept.ersteller}"


        holder.binding.clickcardviewCV.setOnClickListener {
            Log.d("videoweg", "Navigating to RezeptDetailFragment with ID: ${rezept.id}")
            val action = KlausFragmentDirections.actionKlausFragmentToRezeptDetailFragment(rezepte[position].id)
            holder.itemView.findNavController().navigate(action)
        }
    }
}

