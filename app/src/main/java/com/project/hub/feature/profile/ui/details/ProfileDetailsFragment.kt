package com.project.hub.feature.profile.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.hub.R
import com.project.hub.application.di.NetworkModule
import com.project.hub.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Retrofit

@AndroidEntryPoint
class ProfileDetailsFragment : Fragment() {

    private val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTopBarEditBtn()
        setReadMoreBtn()

        binding.myProjectsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.joinedProjectsRv.layoutManager = LinearLayoutManager(requireContext())


        val viewModel: ProfileDetailsViewModel by viewModels()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect { profileDataStates ->
                    when (profileDataStates) {

                        is ProfileDataStates.Success -> {
                            binding.profileName.text = profileDataStates.data.userName
                            binding.descriptionTv.text = profileDataStates.data.description
                            binding.profileMail.text = profileDataStates.data.email

                            val adapterMyProjects =
                                ProfileProjectsAdapter(profileDataStates.data.own_projects)
                            val adapterJoinedProjects =
                                ProfileProjectsAdapter(profileDataStates.data.joined_projects)

                            binding.myProjectsRv.adapter = adapterMyProjects
                            binding.joinedProjectsRv.adapter = adapterJoinedProjects
                        }

                        is ProfileDataStates.Error -> {
                            // TODO: implement error state
                        }

                        is ProfileDataStates.Loading -> {
                            // TODO: implement loading state (mb shimmer)
                        }

                        else -> {}

                    }
                }
            }
        }
    }

    private fun setReadMoreBtn() { // Auto view ReadMore button if count lines of text description > or = 3

        binding.descriptionTv.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.descriptionTv.viewTreeObserver.removeOnGlobalLayoutListener(this);
                if (binding.descriptionTv.layout.lineCount >= 3) binding.btnReadMore.visibility =
                    View.VISIBLE else binding.btnReadMore.visibility = View.GONE
            }
        })

    }

    private fun setTopBarEditBtn() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit -> {
//                    findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
                    true
                }

                else -> false
            }
        }
    }

}