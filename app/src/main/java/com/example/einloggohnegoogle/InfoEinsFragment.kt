package com.example.einloggohnegoogle

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class InfoEinsFragment : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Layout des Fragments inflaten
        val view = inflater.inflate(R.layout.fragment_info_eins, container, false)

        // Zurück-Schaltfläche hinzufügen
        val infoEinsFragmentButton = view.findViewById<Button>(R.id.infoEinsFragmentButton)
        infoEinsFragmentButton.setOnClickListener {
            // Zur vorherigen Activity zurückkehren
            activity?.onBackPressed()
        }

        return view
    }
}