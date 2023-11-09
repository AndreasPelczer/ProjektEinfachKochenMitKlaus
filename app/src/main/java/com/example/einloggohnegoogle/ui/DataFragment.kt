package com.example.einloggohnegoogle.ui


import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.adapter.RezeptAdapter
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.databinding.FragmentDataBinding
import com.example.einloggohnegoogle.viewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.viewModels.menuViewModel.MenuViewModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore

class DataFragment : Fragment() {

    private val firestore = FirebaseFirestore.getInstance()
    private val firestoreDocument =
        FirebaseFirestore.getInstance().collection("Rezepte").document("Rezept")
    val viewModel: FirebaseViewmodel by viewModels()
    private lateinit var binding: FragmentDataBinding
    private lateinit var menuViewModel: MenuViewModel
    private lateinit var drawerLayout: DrawerLayout
    private val rezeptDataList: List<Rezept> = mutableListOf() // Beispiel für die Deklaration
    val bundle = Bundle()
    private lateinit var rezeptAdapter: RezeptAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialisierung der RecyclerView
        binding.rezepteRecyclerView.setHasFixedSize(true)
        rezeptAdapter = RezeptAdapter(
            viewModel,
            viewModel.rezeptDataList.value ?: emptyList(),
            findNavController()
        )
        binding.rezepteRecyclerView.adapter = rezeptAdapter
        binding.rezepteRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Beobachtung der Datenänderungen
        viewModel.rezeptDataList.observe(viewLifecycleOwner) { rezeptDataList ->
            Log.d("FirebaseLoad", "Received data from Firebase: $rezeptDataList")
            rezeptAdapter.updateData(rezeptDataList)
        }

        // Suchfeld-Listener hinzufügen
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("Searches", "Before Text Changed: $s")
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("Searches", "On Text Changed: $s")
            }
            override fun afterTextChanged(s: Editable?) {
                Log.d("Searches", "After Text Changed - Query: $s")
                viewModel.filterNameByChar(s.toString())
            }
        })

        binding.neuesRezeptBTN.setOnClickListener {
            Log.d("ButtonClicked", "Neues Rezept Button wurde geklickt")
            val notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val mediaPlayer = MediaPlayer.create(context, notificationSound)
            mediaPlayer.start()
            val toast =
                Toast.makeText(requireContext(), "Eigenes Rezept erstellen", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
            // Navigationsaktion auslösen
            Log.d("neuesRezept", "weiterleitung")
            findNavController().navigate(R.id.action_dataFragment_to_NeuesRezeptFragment)
        }

        viewModel.user.observe(viewLifecycleOwner) {
            if (it == null) {
            }
        }
        binding.videobuttonBTN.setOnClickListener {
            Log.d("videoweg", "weiterleitung")
            findNavController().navigate(R.id.action_dataFragment_to_VideoFragment)
        }
        binding.tipBTN.setOnClickListener {
            findNavController().navigate(R.id.action_dataFragment_to_tipFragment)
        }
        binding.rezepteBTN.setOnClickListener {
            findNavController().navigate(R.id.dataFragment)
        }
        binding.hamburgermenuimageIV.setOnClickListener {
            openMenu()
        }
        binding.searchBTN.setOnClickListener {
            findNavController().navigate((R.id.action_dataFragment_to_searchFragment))
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navigationView: NavigationView = binding.navView

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item1_settings -> {
                    findNavController().navigate(
                        DataFragmentDirections.actionDataFragmentToSettingsFragment()
                    )
                    closeMenu()
                    true
                }

                R.id.nav_item2_eigenerezepte -> {
                    findNavController().navigate(
                        DataFragmentDirections.actionDataFragmentToKlausFragment()
                    )
                    closeMenu()
                    true
                }

                R.id.nav_item3_logout -> {
                    findNavController().navigate(
                        DataFragmentDirections.actionDataFragmentToLoginFragment()
                    )
                    closeMenu()
                    true
                }
                else -> false
            }
        }
    }

    private fun openMenu() {
        val drawerLayout = binding.drawerLayout
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun closeMenu() {
        val drawerLayout = binding.drawerLayout
        drawerLayout.closeDrawer(GravityCompat.START)
    }
}










