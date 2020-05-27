package de.giphy_clean_architecture.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import de.giphy_androidcleanarchitecture.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpNavigation()
    }

    fun setUpNavigation() {
        val navHostFragment = navHostFragment as NavHostFragment

        NavigationUI.setupWithNavController(
            bottom_navigation,
            navHostFragment.navController
        )
    }
}
