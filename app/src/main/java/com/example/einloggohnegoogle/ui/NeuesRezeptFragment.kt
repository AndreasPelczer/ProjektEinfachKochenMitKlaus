package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.databinding.FragmentNeuesRezeptBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NeuesRezeptFragment : Fragment() {

    private lateinit var binding: FragmentNeuesRezeptBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val viewModel: FirebaseViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNeuesRezeptBinding.inflate(inflater, container, false)
        return binding.root
    }
    // Dropdown-Menuu für die Kategorien.//

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RezeptSpeichernBTN.setOnClickListener {
            try {
                val currentUser = auth.currentUser
                Log.e("BTNgespeichert", "$currentUser")

                // Benutzereingaben aus den EditText-Feldern abrufen
                val name = binding.nameET.text.toString()
                val zutaten = binding.zutatenET.text.toString()
                val zubereitung = binding.zubereitungET.text.toString()
                val videoupload = binding.videoHinzuFGen.text.toString()
                val ersteller = binding.erstellerET.text.toString()

                // Rezept in die Firebase-Datenbank speichern
                viewModel.viewModelScope.launch(Dispatchers.Main) {

                    val userId = getCurrentUserId()
                    Log.e("BTNgespeichert", "$userId")

                    val rezeptData = Rezept(
                        name = name,
                        zutaten = zutaten,
                        zubereitung = zubereitung,
                        videoupload = videoupload,
                        userId = userId.toString(),
                        ersteller = ersteller,
                    )
                    viewModel.insertRezeptData(rezeptData)
                    Log.e("rezept1", "Rezept in Firebasegespeichert")
// Füge das Rezept zur "eigene Rezepte"-Liste hinzu
                    viewModel.insertEigeneRezept(rezeptData)
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


        }
        binding.AbbruchBTN.setOnClickListener {
            // Navigiere zurück zum DataFragment
            findNavController().navigate(R.id.dataFragment)
        }
        val categories = listOf("Suppen", "Vorspeisen", "kleine Gerichte", "Gerichte", "Nachspeisen/Kuchen", "Küchentechnik")

        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, categories)
        binding.categoryET.apply {
            setAdapter(adapter)
            onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                Log.d("DropdownLog", "Ausgewählte Kategorie: $selectedItem")
            }
        }
    }
    private fun getCurrentUserId(): String? {
        // Hier die Methode aufrufen, um die Benutzer-ID zu erhalten.//
        return viewModel.getCurrentUserId()
    }


}

