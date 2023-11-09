package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.einloggohnegoogle.adapter.SearchAdapter
import com.example.einloggohnegoogle.databinding.FragmentSearchBinding
import com.example.einloggohnegoogle.viewModels.FirebaseViewmodel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: FirebaseViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.searchRv

        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.filteredNameList.observe(viewLifecycleOwner) {
            Log.d("logTag", "filterNameByChar aufgerufen mit: $context , $it")
            recyclerView.adapter = SearchAdapter(context,it)

            Log.d("suche","$it,$context")

        }
        binding.searchRv.setHasFixedSize(true)

        // Reagier auf die Nutzereingabe
        binding.searchinputET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nichts zu tun hier
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Nichts zu tun hier
            }
            override fun afterTextChanged(s: Editable?) {
                viewModel.filterNameByChar(s.toString())
            }
        })
        binding.searchBackBTN.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}