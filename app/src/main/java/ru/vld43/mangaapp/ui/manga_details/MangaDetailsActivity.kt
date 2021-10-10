package ru.vld43.mangaapp.ui.manga_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.vld43.mangaapp.R

class MangaDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manga_details)

        val navView: BottomNavigationView = findViewById(R.id.details_bnv)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.details_navigation_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfigurations = AppBarConfiguration(
            setOf(
                R.id.navigation_description,
                R.id.navigation_chapters
            )
        )
        setupActionBarWithNavController(navController, appBarConfigurations)
        navView.setupWithNavController(navController)
    }
}