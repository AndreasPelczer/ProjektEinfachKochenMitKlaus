package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.util.Log
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
import com.example.einloggohnegoogle.adapter.RezeptAdapter
import com.example.einloggohnegoogle.adapter.RezeptListAdapter
import com.example.einloggohnegoogle.data.datamodels.Rezept
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


    private fun setupRecyclerView(rezeptDataList: List<Rezept>) {
        // Annahme: Du hast eine Methode im FirebaseViewModel, die den aktuellen Benutzer zurückgibt
        val currentUser = viewModel.getCurrentUserId()

        // Filtere die Rezepte, die nur vom aktuellen Benutzer erstellt wurden
        val eigeneRezepte = rezeptDataList.filter { it.userId == currentUser}

        // Initialisiere den Adapter mit den gefilterten Rezepten
        val rezeptAdapter = RezeptAdapter(viewModel, eigeneRezepte, findNavController())

        // Verbinde den Adapter mit dem RecyclerView
        binding.eigenerezepteRV.adapter = rezeptAdapter

        // Setze das Layout-Manager für den RecyclerView
        binding.eigenerezepteRV.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eigenerezepteRV.setHasFixedSize(true)

        viewModel.rezeptDataList.observe(viewLifecycleOwner) { rezeptDataList ->
            Log.d("FirebaseLoad", "Received data from Firebase: $rezeptDataList")

            // Hier wird die RecyclerView aktualisiert
            setupRecyclerView(rezeptDataList)
        }
        binding.klausBackBTN.setOnClickListener {
            findNavController().navigate(R.id.dataFragment)
        }



        //RV wird beobachtet
        viewModel.rezeptDataList.observe(viewLifecycleOwner) { rezeptDataList ->
            Log.d("FirebaseLoad", "Received data from Firebase: $rezeptDataList")

            // Initialisiere den Adapter
            val rezeptAdapter = RezeptAdapter(viewModel, rezeptDataList, findNavController())

            // Verbinde den Adapter mit dem RecyclerView
            binding.eigenerezepteRV.adapter = rezeptAdapter

            // Setze das Layout-Manager für den RecyclerView
            binding.eigenerezepteRV.layoutManager = LinearLayoutManager(requireContext())
            setupRecyclerView(rezeptDataList)

        }


    }


}
