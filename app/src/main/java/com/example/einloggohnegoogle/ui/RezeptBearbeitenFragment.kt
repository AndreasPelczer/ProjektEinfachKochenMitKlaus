package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.databinding.FragmentRezeptBearbeitenBinding
import com.google.firebase.firestore.FirebaseFirestore

class RezeptBearbeitenFragment : Fragment() {

    private lateinit var binding: FragmentRezeptBearbeitenBinding
    private val firestore = FirebaseFirestore.getInstance()
    val viewModel: FirebaseViewmodel by viewModels()
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRezeptBearbeitenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadRezeptDetail(id)
        viewModel.rezeptdetail.observe(viewLifecycleOwner) { item ->
            binding.editRezeptNameTV.setText(item.name)
            binding.editRezeptZutatenTV.setText(item.zutaten)
            binding.editRezeptZubereitungTV.setText(item.zutaten)
            binding.editRezeptErstellerTV2.setText(item.ersteller)
        }
        // Informationen in die Eingabefelder
        binding.ZurueckRezeptBearbeitenBTN.setOnClickListener {
            Log.d("bearbeitenZurückBTN", "rückleitung an Detail")

            findNavController().navigate(R.id.rezeptDetailFragment)
        }

        binding.RezeptSpeichernBTN.setOnClickListener {
            Log.e("Buttonrezepspeichern Button gespeichert", "Rezept gespeichert")


            val updatedName = binding.editRezeptNameTV.text.toString()
            val updatedZutaten = binding.editRezeptZutatenTV.text.toString()
            val updatedZubereitung = binding.editRezeptZubereitungTV.text.toString()
            val updatedErsteller = binding.editRezeptErstellerTV2.text.toString()


            // beitrag aktualisieren.//
            if (id != null) {
                viewModel.updateRezeptDetail(
                    id!!,
                    updatedName,
                    updatedZutaten,
                    updatedZubereitung,
                    updatedErsteller
                )
                val navController = findNavController()
                navController.navigate(RezeptBearbeitenFragmentDirections.actionRezeptBearbeitenFragmentToDataFragment())
            }


        }

    }
}


