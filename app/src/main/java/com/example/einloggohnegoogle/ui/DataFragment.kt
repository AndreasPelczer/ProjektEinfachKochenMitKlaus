package com.example.einloggohnegoogle.ui


import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.ViewModels.MenuViewModel.MenuViewModel
import com.example.einloggohnegoogle.adapter.RezeptAdapter
import com.example.einloggohnegoogle.data.datamodels.MenuState
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.databinding.FragmentDataBinding
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
    override fun onResume() {
        super.onResume()

        // Hier rufst du die Methode auf, um Daten zu laden oder zu aktualisieren
        viewModel.loadfromFireStore()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadfromFireStore()
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupRecyclerView(rezeptDataList: List<Rezept>) {
        // Initialisiere den Adapter
        val rezeptAdapter = RezeptAdapter(viewModel, rezeptDataList, findNavController())

        // Verbinde den Adapter mit dem RecyclerView
        binding.rezepteRecyclerView.adapter = rezeptAdapter

        // Setze das Layout-Manager für den RecyclerView
        binding.rezepteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rezepteRecyclerView.setHasFixedSize(true)

        viewModel.rezeptDataList.observe(viewLifecycleOwner) { rezeptDataList ->
            Log.d("FirebaseLoad", "Received data from Firebase: $rezeptDataList")

            // Hier wird die RecyclerView aktualisiert
            setupRecyclerView(rezeptDataList)
        }

        menuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        // Beobachte die LiveData "menuState" im ViewModel
        menuViewModel.menuState.observe(viewLifecycleOwner) { menuState: MenuState ->
            // Hier wird  der Menüzustand in der UI aktualisiert
            if (menuState.isOpen) {
                // Das Menü ist geöffnet, führe die entsprechende UI-Aktion durch
                // openMenu()
            } else {
                // closeMenu()
                // Das Menü ist geschlossen, führe die entsprechende UI-Aktion durch
            }
        }

        //RV wird beobachtet
        viewModel.rezeptDataList.observe(viewLifecycleOwner) { rezeptDataList ->
            Log.d("FirebaseLoad", "Received data from Firebase: $rezeptDataList")

            // Initialisiere den Adapter
            val rezeptAdapter = RezeptAdapter(viewModel, rezeptDataList, findNavController())

            // Verbinde den Adapter mit dem RecyclerView
            binding.rezepteRecyclerView.adapter = rezeptAdapter

            // Setze das Layout-Manager für den RecyclerView
            binding.rezepteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            setupRecyclerView(rezeptDataList)

        }


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
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navigationView: NavigationView = binding.navView

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item1_settings -> {
                    //TODO: facts: funktioniert mit einem random api call.//
                  /*  findNavController().navigate(
                        DataFragmentDirections.actionHomeFragmentToFactsFragment()
                    )*/
                    closeMenu()
                    true
                }

                R.id.nav_item2_chat -> {
                    //TODO: (feature: Chat, funktioniert. ausbau als Bonus.//
                  /*  findNavController().navigate(
                      HomeFragmentDirections.actionHomeFragmentToChatFragment("chat")
                    )*/
                    closeMenu()
                    true
                }

                R.id.nav_item3_logout -> {
                    viewModel.signOut()
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










