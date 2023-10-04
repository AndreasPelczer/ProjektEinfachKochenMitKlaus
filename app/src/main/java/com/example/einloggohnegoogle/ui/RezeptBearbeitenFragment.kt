package com.example.einloggohnegoogle.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.databinding.FragmentRezeptBearbeitenBinding
import com.example.einloggohnegoogle.databinding.FragmentRezeptDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore

class RezeptBearbeitenFragment : Fragment() {

    private lateinit var binding: FragmentRezeptBearbeitenBinding
    private val firestore = FirebaseFirestore.getInstance()
    val viewModel: FirebaseViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRezeptBearbeitenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RezeptSpeichernBTN.setOnClickListener {
            Log.e("Buttonrezepspeichern Button gespeichert","Rezept gespeichert")

            // Benutzereingaben aus den EditText-Feldern abrufen
            val name = binding.editRezeptNameTV.text.toString()
            val zutaten = binding.editRezeptZutatenTV.text.toString()
            val zubereitung = binding.editRezeptZubereitungTV.text.toString()

            // Rezept in die Firebase-Datenbank speichern
            viewModel.viewModelScope.launch(Dispatchers.Main) {
                val rezeptData = Rezept(
                    name = name,
                    zutaten = zutaten,
                    zubereitung = zubereitung,

                )
                viewModel.insertRezeptData(rezeptData)
                Log.e("rezept gespeichert","Rezept in Firebasegespeichert")

                // Navigiere zurück zum DataFragment
                findNavController().navigate(R.id.dataFragment)
            }
        }
        fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Annahme: Informationen aus dem vorherigen Fragment übergeben
            val rezeptName = arguments?.getString("rezeptName") ?: ""
            val rezeptZutaten = arguments?.getString("rezeptZutaten") ?: ""
            val rezeptZubereitung = arguments?.getString("rezeptZubereitung") ?: ""

            // Setze die Informationen in die Eingabefelder
            binding.editRezeptNameTV.setText(rezeptName)
            binding.editRezeptZutatenTV.setText(rezeptZutaten)
            binding.editRezeptZubereitungTV.setText(rezeptZubereitung)
        }
    }
}


