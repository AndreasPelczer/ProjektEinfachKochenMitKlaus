package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.databinding.FragmentRezeptDetailBinding
import java.lang.reflect.Array.getInt

class RezeptDetailFragment : Fragment() {

    private val viewModel: FirebaseViewmodel by viewModels()
    private lateinit var binding: FragmentRezeptDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRezeptDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            val id = it.getString("id")
            viewModel.loadRezeptDetail(id)
            Log.d("loadDetail", " to RezeptDetailFragment with ID: $id")

            viewModel.rezeptdetail.observe(viewLifecycleOwner) {item->
                binding.RezeptNameTV.text = item.name
                Log.d("RezeptName", " to RezeptDetailFragment with ID: $id")

                binding.RezeptZutatenTV.text = item.zutaten
                Log.d("RezeptZutaten", " to RezeptDetailFragment with ID: $id")

                binding.RezeptZubereitungTV.text = item.zubereitung
                Log.d("RezeptZubereitung", " to RezeptDetailFragment with ID: $id")

                binding.rezeptBearbeitenBTN.setOnClickListener {
                    val navController = findNavController()
                    navController.navigate(RezeptDetailFragmentDirections.actionRezeptDetailFragmentToRezeptBearbeitenFragment(id!!))
                }
                binding.erstellerTV.text=item.ersteller

            }
        }

        binding.RezeptBackBTN.setOnClickListener {
            val navController = findNavController()
            navController.navigate(RezeptDetailFragmentDirections.actionRezeptDetailFragmentToDataFragment())
        }


        binding.rezeptLoeschenBTN.setOnClickListener {
            Toast.makeText(requireContext(), "Keine Berechtigung", Toast.LENGTH_SHORT).show()
            // Zeige einen Toast an, wenn keine Berechtigung zum Löschen vorhanden ist
        }


    }

}

