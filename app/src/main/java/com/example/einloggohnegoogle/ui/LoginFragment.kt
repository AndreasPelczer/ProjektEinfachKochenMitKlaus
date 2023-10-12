package com.example.einloggohnegoogle.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.databinding.FragmentLoginBinding


@Suppress("NAME_SHADOWING")
class LoginFragment : Fragment() {

    val viewmodel: FirebaseViewmodel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpBTN.setOnClickListener {
            Log.e("signup", "App signtup ")

            val email = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Bitte E-Mail und Passwort eingeben",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewmodel.signUp(email, password)
            }
            binding.exitBTN.setOnClickListener {
                Log.e("beenden", "App beendet")

                activity?.finish()
            }

            binding.signInBTN.setOnClickListener {
                Log.e("eingelogt", "App logt ein")

                val email = binding.emailET.text.toString()
                val password = binding.passwordET.text.toString()
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Bitte E-Mail und Passwort eingeben",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.e("eingelogt", "App logt ein")

                    viewmodel.signIn(email, password)
                }

                //Wenn User eingeloggt ist, navigiere weiter
                viewmodel.user.observe(viewLifecycleOwner) {
                    if (it != null) {
                        findNavController().navigate(R.id.dataFragment)
                    }
                }

            }
        }
    }
}

