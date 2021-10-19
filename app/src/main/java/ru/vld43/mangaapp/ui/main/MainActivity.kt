package ru.vld43.mangaapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.vld43.mangaapp.R
import ru.vld43.mangaapp.di.activity.ActivityModule
import ru.vld43.mangaapp.di.activity.DaggerActivityComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        DaggerActivityComponent.builder()
//            .activityModule(ActivityModule(this))
//            .build()
//            .inject(this)

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