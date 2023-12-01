package com.example.youtubeclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.beletvideoexample.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navViewBottomBar: BottomNavigationView = binding.navViewBottomBar
        val navController = findNavController(com.example.beletvideoexample.R.id.nav_host_fragment_activity_main)

        navViewBottomBar.setupWithNavController(navController)
    }
}