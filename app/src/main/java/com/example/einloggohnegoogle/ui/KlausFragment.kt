package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.adapter.RezeptListAdapter
import com.example.einloggohnegoogle.databinding.FragmentKlausBinding
import com.google.firebase.firestore.FirebaseFirestore


class KlausFragment : Fragment() {

    private lateinit var binding: FragmentKlausBinding
    private val viewModel: FirebaseViewmodel by viewModels()
    private val firestore = FirebaseFirestore.getInstance()
    private val firestoreDocument =
        FirebaseFirestore.getInstance().collection("Rezepte").document("Rezept")

    override fun onResume() {
        super.onResume()
        // Hier rufst du die Methode auf, um Daten zu laden oder zu aktualisieren
        viewModel.loadfromFireStore()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentKlausBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.klausBackBTN.setOnClickListener {
            findNavController().navigate(R.id.dataFragment)
        }
        val rezepte = viewModel.getOwnUserRezepte(userId = "")
        val recyclerView: RecyclerView = view.findViewById(R.id.eigenerezepteRV)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RezeptListAdapter(rezepte)  // Use RezeptListAdapter




    }


}
