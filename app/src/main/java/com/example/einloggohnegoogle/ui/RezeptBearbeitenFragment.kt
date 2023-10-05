package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.databinding.FragmentRezeptBearbeitenBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        viewModel.rezeptdetail.observe(viewLifecycleOwner) { item ->
            binding.editRezeptNameTV.setText(item.name)
            binding.editRezeptZutatenTV.setText(item.zutaten)
            binding.editRezeptZubereitungTV.setText(item.zutaten)
        }
        // Informationen in die Eingabefelder
        binding.ZurueckRezeptBearbeitenBTN.setOnClickListener {
            Log.d("bearbeitenZurückBTN", "rückleitung an Detail")

            findNavController().navigate(R.id.rezeptDetailFragment)
        }

        binding.RezeptSpeichernBTN.setOnClickListener {
            Log.e("Buttonrezepspeichern Button gespeichert", "Rezept gespeichert")

            // Benutzereingaben aus den EditText-Feldern abrufen
            val name = binding.editRezeptNameTV.text.toString()
            val zutaten = binding.editRezeptZutatenTV.text.toString()
            val zubereitung = binding.editRezeptZubereitungTV.text.toString()

            // Rezept in die Firebase-Datenbank speichern
            viewModel.viewModelScope.launch(Dispatchers.Main) {
                val rezeptData = Rezept(
                    id = id.toString(),
                    name = name,
                    zutaten = zutaten,
                    zubereitung = zubereitung,
                )
                viewModel.insertRezeptData(rezeptData)
                Log.e("rezept gespeichert", "Rezept in Firebasegespeichert")

                // Navigiere zurück zum DataFragment
                findNavController().navigate(R.id.dataFragment)
            }

        }

    }
}


