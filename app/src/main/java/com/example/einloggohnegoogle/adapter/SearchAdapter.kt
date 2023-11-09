package com.example.einloggohnegoogle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.databinding.ListItemResultBinding
import com.example.einloggohnegoogle.ui.SearchFragmentDirections

class SearchAdapter(
    private val context: Context?,
    private val dataset: List<Rezept> = emptyList()
)

    : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemResultBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rezept = dataset[position]

        holder.binding.searchCardCV.setOnClickListener {
            it.findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToRezeptDetailFragment(rezept.id))
        }

        holder.binding.rezeptNameTV2.text = rezept.name
        holder.binding.zutatenTV2.text = rezept.zutaten
        holder.binding.zubereitungTV2.text = rezept.zubereitung
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
