package com.project.hub.feature.auth.ui.signup

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.project.hub.R
import com.project.hub.databinding.FragmentSignupBinding
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
    private lateinit var dialog: AlertDialog

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
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

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
                viewModel.state.collect { state ->
                    when (state) {
                        is SignUpState.Error -> {

                            snackBarError()

                            with(binding) {
                                tiEmail.isErrorEnabled = true
                                tiPass.isErrorEnabled = true
                                tiName.isErrorEnabled = true
                                tiEmail.error = " "
                                tiPass.error = " "
                                tiPass.errorIconDrawable = null
                                tiName.error = " "
                            }

                            dialog.dismiss()
                        }

                        is SignUpState.Success -> {
                            dialog.dismiss()

                            with(binding) {
                                tiEmail.isErrorEnabled = false
                                tiPass.isErrorEnabled = false
                                tiName.isErrorEnabled = false
                            }

                        }

                        is SignUpState.Loading -> {
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