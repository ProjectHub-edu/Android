package com.project.hub.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.project.hub.R
import com.project.hub.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

//        supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment

    }
}