package com.example.einloggohnegoogle.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.viewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.databinding.FragmentLoginBinding


@Suppress("NAME_SHADOWING")
class LoginFragment : Fragment() {

    private val viewModel: FirebaseViewmodel by viewModels()
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

        binding.btnLogin.setOnClickListener {
            Log.e("signup", "App signtup ")
            val loginViewModel: FirebaseViewmodel by viewModels()

            val email = binding.etEmailRegister.text.toString()
            val password = binding.etPasswordRegister.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Bitte E-Mail und Passwort eingeben",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                loginViewModel.signIn(email, password)
            }

            binding.btnRegister.setOnClickListener {
                Log.e("eingelogt", "App logt ein")

                val email = binding.etEmailLogin.text.toString()
                val password = binding.etPasswordLogin.text.toString()
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Bitte E-Mail und Passwort eingeben",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.e("eingelogt", "App logt ein")

                    loginViewModel.signUp(email, password)
                }

                //Wenn User eingeloggt ist, navigiere weiter
                viewModel.user.observe(viewLifecycleOwner) {
                    if (it == null) { // not logged in
                        binding.tvLoggedIn.text = "You are not logged in"
                    } else
                        if (it != null) {
                        binding.tvLoggedIn.text = "You are logged in!"
                        findNavController().navigate(R.id.dataFragment)
                    }
                }

            }
        }
    }


    override fun onStart() {
        super.onStart()
    }


}

