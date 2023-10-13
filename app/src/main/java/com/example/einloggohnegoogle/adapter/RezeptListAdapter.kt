package com.example.einloggohnegoogle.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.data.datamodels.Rezept


class RezeptListAdapter(private val rezepte: LiveData<List<Rezept>>) : RecyclerView.Adapter<RezeptListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.cardViewCV)
        // Weitere Views hier...

        // Methode zum Binden der Daten an die Views
        fun bind(item: Rezept) {
            textViewName.text = item.name
            // Weitere Views binden...
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rezept, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        rezepte.value?.let { recipes ->
            holder.bind(recipes[position])
        }
    }

    override fun getItemCount(): Int {
        return rezepte.value?.size ?:0
    }
}

