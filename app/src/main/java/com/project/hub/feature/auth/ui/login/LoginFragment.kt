package com.project.hub.feature.auth.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import com.project.hub.R
import com.project.hub.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val binding: FragmentLoginBinding by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    private val viewModel: LoginFragmentViewModel by viewModels()
//    private val args: LoginFragmentViewModel by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val email = binding.tieEmail.text.toString()
            val password = binding.tiePass.text.toString()
            viewModel.login(email, password)
        }

        // Action Bar
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = null
        actionBar?.setDisplayHomeAsUpEnabled(false)

        // Navigate to Register
        val navController = Navigation.findNavController(view)
        binding.registerButton.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_signupFragment)
        }

        // Show Error
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.exceptionState.collect { state ->
                    when (state) {
                        is LoginState.Error -> {
                            binding.tiEmail.error = state.throwable
//                            binding.tiPass.error = state.throwable
//                            binding.tiPass.boxStrokeColor = Color.RED
                        }
                        is LoginState.Success -> {}
                        is LoginState.Loading -> {}
                    }
                }
            }
        }


    }
}