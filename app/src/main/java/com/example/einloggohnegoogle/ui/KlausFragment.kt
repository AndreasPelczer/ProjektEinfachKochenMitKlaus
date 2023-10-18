package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.viewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.viewModels.menuViewModel.MenuViewModel
import com.example.einloggohnegoogle.adapter.RezeptAdapter
import com.example.einloggohnegoogle.adapter.RezeptListAdapter
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.databinding.FragmentKlausBinding
import com.google.firebase.firestore.FirebaseFirestore


class KlausFragment : Fragment() {


    private val firestore = FirebaseFirestore.getInstance()
    private val firestoreDocument =
        FirebaseFirestore.getInstance().collection("Rezepte").document("Rezept")
    val viewModel: FirebaseViewmodel by viewModels()
    private lateinit var binding: FragmentKlausBinding
    private lateinit var menuViewModel: MenuViewModel
    private lateinit var drawerLayout: DrawerLayout


    override fun onResume() {
        super.onResume()

        // Hier rufst du die Methode auf, um Daten zu laden oder zu aktualisieren
        viewModel.loadfromFireStore()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadfromFireStore()
        binding = FragmentKlausBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun setupRecyclerView(rezeptDataList: List<Rezept>) {
        val currentUser = viewModel.getCurrentUserId()
        val eigeneRezepte = rezeptDataList.filter { it.userId == currentUser }
        val rezeptAdapter = RezeptListAdapter(eigeneRezepte) { selectedRezept ->
            val action = KlausFragmentDirections.actionKlausFragmentToRezeptDetailFragment(
                selectedRezept.toString()
            )
            findNavController().navigate(action)
        }

        binding.eigenerezepteRV.adapter = rezeptAdapter
        binding.eigenerezepteRV.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eigenerezepteRV.setHasFixedSize(true)

        viewModel.rezeptDataList.observe(viewLifecycleOwner) { rezeptDataList ->
            Log.d("FirebaseLoad", "Received data from Firebase: $rezeptDataList")
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
