package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.transform.RoundedCornersTransformation
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.databinding.FragmentRezeptDetailBinding

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
            Log.d("loadDetail", " to RezeptDetailFragment with ID: ${id}")

            viewModel.rezeptdetail.observe(viewLifecycleOwner) {
                binding.RezeptNameTV.text = it.name
                binding.RezeptZutatenTV.text = it.zutaten
                binding.RezeptZubereitungTV.text = it.zubereitung
            }
        }

        binding.RezeptBackBTN.setOnClickListener {
            val navController = findNavController()
            navController.navigate(RezeptDetailFragmentDirections.actionRezeptDetailFragmentToDataFragment())
        }

        binding.rezeptBearbeitenBTN.setOnClickListener {
            // Nehmen an, dass die id nicht null ist
            val id = arguments?.getString("id") ?: ""
            navigateToRezeptBearbeiten(id)
        }

        binding.rezeptLoeschenBTN.setOnClickListener{
            Toast.makeText(requireContext(), "Keine Berechtigung", Toast.LENGTH_SHORT).show()
            // Zeige einen Toast an, wenn keine Berechtigung zum Löschen vorhanden ist
        }
    }

    private fun navigateToRezeptBearbeiten(rezeptId: String) {
        val action = RezeptDetailFragmentDirections.actionRezeptDetailFragmentToRezeptBearbeitenFragment(
            id = rezeptId,
            rezeptName = binding.RezeptNameTV.text.toString(),
            rezeptZutaten = binding.RezeptZutatenTV.text.toString(),
            rezeptZubereitung = binding.RezeptZubereitungTV.text.toString()
        )
        findNavController().navigate(action)
    }
}
