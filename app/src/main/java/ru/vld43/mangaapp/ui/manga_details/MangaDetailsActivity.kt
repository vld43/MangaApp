package ru.vld43.mangaapp.ui.manga_details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.vld43.mangaapp.R
import ru.vld43.mangaapp.databinding.ActivityMangaDetailsBinding
import ru.vld43.mangaapp.domain.DataManga
import ru.vld43.mangaapp.ui.description.DescriptionFragment

class MangaDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: MangaDetailsViewModel
    private lateinit var binding: ActivityMangaDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MangaDetailsViewModel::class.java]
        binding = ActivityMangaDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

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