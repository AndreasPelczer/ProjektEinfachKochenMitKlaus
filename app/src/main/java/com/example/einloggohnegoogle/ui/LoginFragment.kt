package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    // Initialisiere das ViewModel
    val viewmodel: FirebaseViewmodel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setze einen Click-Listener für die Registrierung
        binding.signUpBTN.setOnClickListener {
            val email = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()

            try {
                // Versuche die Registrierung
                viewmodel.signUp(email, password)
            } catch (e: Exception) {
                // Bei einem Fehler, Log-Ausgabe
                Log.e(TAG, "Fehler bei der Registrierung: ${e.message}")
            }
        }

        // Setze einen Click-Listener für die Anmeldung
        binding.signInBTN.setOnClickListener {
            val email = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()

            try {
                // Versuche die Anmeldung
                viewmodel.signIn(email, password)
            } catch (e: Exception) {
                // Bei einem Fehler, Log-Ausgabe
                Log.e(TAG, "Fehler bei der Anmeldung: ${e.message}")
            }
        }

        // Wenn der User eingeloggt ist, navigiere weiter
        viewmodel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                findNavController().navigate(R.id.dataFragment)
            }
        }
    }

    companion object {
        private const val TAG = "LoginFragment" // TAG für Log-Nachrichten
    }
}
