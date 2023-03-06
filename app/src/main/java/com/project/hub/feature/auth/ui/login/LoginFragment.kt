package com.project.hub.feature.auth.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.project.hub.R
import com.project.hub.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val binding: FragmentLoginBinding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginFragmentViewModel by viewModels()
    private lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Login btn click
        binding.loginButton.setOnClickListener {
            val email = binding.tieEmail.text.toString()
            val password = binding.tiePass.text.toString()

            // Invoke
            viewModel.login(email, password)
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
                viewModel.state.collect { state ->
                    when (state) {
                        is LoginState.Error -> {

                            snackBarError()

                            with(binding) {
                                tiEmail.isErrorEnabled = true
                                tiPass.isErrorEnabled = true
                                tiEmail.error = " "
                                tiPass.error = " "
                                tiPass.errorIconDrawable = null
                            }


                            dialog.dismiss()

                        }

                        is LoginState.Success -> {
                            dialog.dismiss()

                            with(binding) {
                                tiEmail.isErrorEnabled = false
                                tiPass.isErrorEnabled = false
                            }

                        }

                        is LoginState.Loading -> {
                            dialogWithProgress()
                        }
                    }
                }
            }
        }
    }

    private fun dialogWithProgress(): AlertDialog {

        val builder = MaterialAlertDialogBuilder(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        builder.setView(dialogView)

        dialog = builder.show()
        dialog.setCancelable(false)

        val window = dialog.window
        window?.setLayout(500, 800)

        return dialog

    }

    private fun snackBarError() {
        val snackBar = view?.let {
            Snackbar.make(it, resources.getText(R.string.some_error_occurred), Snackbar.LENGTH_LONG)
        }
        val color = view?.let { MaterialColors.getColor(it, androidx.appcompat.R.attr.colorError) }
        if (color != null) {
            snackBar?.setBackgroundTint(color)
        }
        snackBar?.show()
    }

}