package com.project.hub.feature.auth.ui.signup

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.*
import android.widget.Toolbar
import android.window.OnBackInvokedCallback
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.project.hub.R
import com.project.hub.databinding.FragmentSignupBinding
import com.project.hub.feature.auth.ui.login.LoginFragmentViewModel
import com.project.hub.feature.auth.ui.login.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private val binding: FragmentSignupBinding by lazy {
        FragmentSignupBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Action Bar
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = getString(R.string.register)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val navController = findNavController()

        // Host Menu (App Bar)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) { }

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

        // SignUp Click
        binding.signUpButton.setOnClickListener {
            val email = binding.tieEmail.text.toString()
            val password = binding.tiePass.text.toString()
            val username = binding.tiePass.text.toString()

            // Invoke
            viewModel.register(email, password, username)
        }

        // Error Flow
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.exceptionState.collect { state ->
                    when (state) {
                        is SignUpState.Error -> {
                            binding.tiEmail.error = state.throwable
//                            binding.tiPass.error = state.throwable
//                            binding.tiPass.boxStrokeColor = Color.RED
                        }
                        is SignUpState.Success -> {}
                        is SignUpState.Loading -> {}
                    }
                }
            }
        }
    }
}