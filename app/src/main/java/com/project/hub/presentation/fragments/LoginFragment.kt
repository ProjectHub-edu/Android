package com.project.hub.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.hub.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

//    private var _binding: FragmentLoginBinding? = null
//    private val binding get() = _binding!!
//
//
//
//    // Fragment onCreateView
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentLoginBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    private val binding: FragmentLoginBinding by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root



    // Fragment onDestroyView
//    override fun onDestroyView() {
//        _binding = null
//        super.onDestroyView()
//    }
}