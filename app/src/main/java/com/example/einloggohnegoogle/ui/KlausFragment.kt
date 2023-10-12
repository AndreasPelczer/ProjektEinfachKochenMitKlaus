package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.databinding.FragmentKlausBinding
import com.example.einloggohnegoogle.databinding.FragmentSettingsBinding

class KlausFragment : Fragment() {

    private lateinit var binding: FragmentKlausBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.klausBackBTN.setOnClickListener {
            findNavController().navigate(R.id.dataFragment)

        }
    }
}