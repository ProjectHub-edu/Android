package com.project.hub.feature.auth.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.project.hub.R
import com.project.hub.databinding.FragmentSignupBinding
import com.project.hub.feature.auth.ui.login.LoginFragmentViewModel
import com.project.hub.feature.auth.ui.login.LoginState
import kotlinx.coroutines.launch

class SignupFragment : Fragment() {

    private val binding: FragmentSignupBinding by lazy {
        FragmentSignupBinding.inflate(
            layoutInflater
        )
    }

//    private val viewModel: SignupViewModel by viewModels()
//    private val args: LoginFragmentViewModel by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val navController = findNavController(R.id.nav)


//        binding.loginButton.setOnClickListener {
//            val email = binding.tieEmail.text.toString()
//            val password = binding.tiePass.text.toString()
//            viewModel.login(email, password)
//        }


//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.CREATED) {
//                viewModel.exceptionState.collect { state ->
//                    when (state) {
//                        is LoginState.Error -> {
//                            binding.tiEmail.error = state.throwable
////                            binding.tiPass.error = state.throwable
////                            binding.tiPass.boxStrokeColor = Color.RED
//                        }
//                        is LoginState.Success -> {}
//                        is LoginState.Loading -> {}
//                    }
//                }
//            }
//        }


    }
}