package com.project.hub.feature.auth.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.project.hub.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

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

        val email = "vova1902@gmail.com"
        val password = "qwerty123"

        // VM
//        viewModel = LoginFragmentViewModel()

        binding.loginButton.setOnClickListener {
            viewModel.login(email, password)
        }

    }
}