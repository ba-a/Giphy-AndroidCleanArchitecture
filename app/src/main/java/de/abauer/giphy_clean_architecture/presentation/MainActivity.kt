package de.abauer.giphy_clean_architecture.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import de.abauer.giphy_androidcleanarchitecture.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val finalHost = NavHostFragment.create(R.navigation.nav_graph)
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_container, finalHost, NAV_TAG)
                .setPrimaryNavigationFragment(finalHost) // equivalent to app:defaultNavHost="true"
                .commitNow()

            setUpNavigation(finalHost)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setUpNavigation(supportFragmentManager.findFragmentByTag(NAV_TAG) as NavHostFragment)
    }

    private fun setUpNavigation(navFragment: NavHostFragment) {
        NavigationUI.setupWithNavController(
            bottom_navigation,
            navFragment.navController
        )
    }

    companion object {
        private const val NAV_TAG = "NAV_TAG"
    }
}
