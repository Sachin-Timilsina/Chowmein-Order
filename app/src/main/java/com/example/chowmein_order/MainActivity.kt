package com.example.chowmein_order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    //Setup as late init var to initialize later and have the overall scope.
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /*
        Accessing the NavController from navHostFragment which
        acts as fragment manager.
         */
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        // Set up the action bar with the nav controller.
        setupActionBarWithNavController(navController)

    }

    // Handle the navigate back when the Navigation up btn is clicked!
    override fun onSupportNavigateUp(): Boolean {
        // Handle the first specified action or just implement the default function (ShortCircuit.)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}