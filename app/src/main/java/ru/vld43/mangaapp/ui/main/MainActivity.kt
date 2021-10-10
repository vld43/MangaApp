package ru.vld43.mangaapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.vld43.mangaapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.main_bnv)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_navigation_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfigurations = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_bookmarks,
            )
        )
        setupActionBarWithNavController(navController, appBarConfigurations)
        navView.setupWithNavController(navController)
    }
}