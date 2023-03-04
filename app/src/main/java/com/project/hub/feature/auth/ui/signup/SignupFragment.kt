package com.project.hub.feature.auth.ui.signup

import android.os.Bundle
import android.view.*
import android.window.OnBackInvokedCallback
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
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

        // Action Bar
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = "Regiter"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val navController = findNavController()

        // Host Menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {  }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    android.R.id.home -> {
                        navController.popBackStack()
                        true
                    }
                    else -> {
                        false
                    }
                }

            }
        })


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