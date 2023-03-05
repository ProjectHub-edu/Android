package com.project.hub.feature.auth.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.project.hub.R
import com.project.hub.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val binding: FragmentLoginBinding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private lateinit var overlayView: View

    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Loading Overlay
        overlayView = layoutInflater.inflate(R.layout.overlay_layout, binding.root, false)

        // Login btn click
        binding.loginButton.setOnClickListener {
            val email = binding.tieEmail.text.toString()
            val password = binding.tiePass.text.toString()

            // Invoke
            viewModel.login(email, password)
            showOverlay()

        }

        // Action Bar
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = null
        actionBar?.setDisplayHomeAsUpEnabled(false)

        // Navigate to Register
        val navController = Navigation.findNavController(view)
        binding.registerButton.setOnClickListener {
            navController.navigate(
                R.id.action_loginFragment_to_signupFragment
            )
        }

        // Show Error
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.exceptionState.collect { state ->
                    when (state) {
                        is LoginState.Error -> {
                            binding.tiEmail.error = state.throwable
                            binding.root.removeView(overlayView)
                        }
                        is LoginState.Success -> {
                            binding.root.removeView(overlayView)
                        }
                        is LoginState.Loading -> {
                            showOverlay()
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showOverlay() {
        binding.root.addView(overlayView)
        overlayView.setOnTouchListener { _, _ -> true }
    }

    override fun onDestroyView() {
        binding.root.removeView(overlayView)
        super.onDestroyView()
    }

}