package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.data.datamodels.Profile
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.databinding.FragmentNeuesRezeptBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class NeuesRezeptFragment : Fragment() {

    private lateinit var binding: FragmentNeuesRezeptBinding
    private val firestore = FirebaseFirestore.getInstance()
    private val viewModel: FirebaseViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNeuesRezeptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RezeptSpeichernBTN.setOnClickListener {
            try {

                Log.e("Buttonrezepspeichern Button gespeichert", "Rezept gespeichert")
                val userId = viewModel.getCurrentUserId()
                // Benutzereingaben aus den EditText-Feldern abrufen
                val name = binding.nameET.text.toString()
                val zutaten = binding.zutatenET.text.toString()
                val zubereitung = binding.zubereitungET.text.toString()
                val videoupload = binding.videoHinzuFGen.text.toString()
                val ersteller = binding.erstellerET.text.toString()

                // Rezept in die Firebase-Datenbank speichern
                viewModel.viewModelScope.launch(Dispatchers.Main) {
                    val rezeptData = Rezept(
                        name = name,
                        zutaten = zutaten,
                        zubereitung = zubereitung,
                        videoupload = videoupload,
                        userId = userId.toString(),
                        ersteller = ersteller
                    )
                    viewModel.insertRezeptData(rezeptData)
                    Log.e("rezept1", "Rezept in Firebasegespeichert")

                    // Navigiere zurück zum DataFragment
                    findNavController().navigate(R.id.dataFragment)
                }

            } catch (e: Exception) {
                Log.e("Error", "Fehler beim Speichern des Rezepts: ${e.message}", e)
                Toast.makeText(
                    requireContext(),
                    "Fehler beim Speichern des Rezepts",
                    Toast.LENGTH_SHORT
                ).show()
            }

            binding.AbbruchBTN.setOnClickListener {
                // Navigiere zurück zum DataFragment
                findNavController().navigate(R.id.dataFragment)
            }
        }
    }
}

