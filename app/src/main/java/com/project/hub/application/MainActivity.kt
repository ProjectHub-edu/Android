package com.project.hub.application

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.view.MenuProvider
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.project.hub.R
import com.project.hub.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //    @SuppressLint("ObjectAnimatorBinding")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT

        // Binding
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        // TopAppBar
        val topAppBar = binding.topAppBar
        val topAppBarLayout = binding.topAppBarLayout
        setSupportActionBar(topAppBar)

        // Anim
        val transition = LayoutTransition()
        transition.setAnimator(
            LayoutTransition.CHANGE_APPEARING, ObjectAnimator.ofFloat(
                topAppBar,
                "alpha",
                0f, 1f
            )
        )

        topAppBar.layoutTransition = transition
    }
}